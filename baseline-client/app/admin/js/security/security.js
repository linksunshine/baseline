angular.module("security", ["ngResource", "ngRoute"])
    .constant('AUTH_EVENTS', {
        loginSuccess: 'auth-login-success',
        loginFailed: 'auth-login-failed',
        logoutSuccess: 'auth-logout-success',
        sessionTimeout: 'auth-session-timeout',
        notAuthenticated: 'auth-not-authenticated',
        notAuthorized: 'auth-not-authorized'
    })
    .constant('USER_PERMISSION', {
        USER_VIEW: 'USER:1',
        USER_CREATE: 'USER:2',
        USER_UPDATE: 'USER:4',
        USER_DELETE: 'USER:8',
        USER_SEARCH: 'USER:16',
        USER_ALLOCATE: 'USER:32',

        ROLE_VIEW: 'ROLE:1',
        ROLE_CREATE: 'ROLE:2',
        ROLE_UPDATE: 'ROLE:4',
        ROLE_DELETE: 'ROLE:8',
        ROLE_SEARCH: 'ROLE:16',
        ROLE_ALLOCATE: 'ROLE:32',

        PERMISSION_VIEW: 'PERMISSION:1',
        PERMISSION_CREATE: 'PERMISSION:2',
        PERMISSION_UPDATE: 'PERMISSION:4',
        PERMISSION_DELETE: 'PERMISSION:8',
        PERMISSION_SEARCH: 'PERMISSION:16'
    })
    .service('Session', ['$cookieStore', function ($cookieStore) {
        this.create = function (userId, userRole, userPermission) {
            this.globals = {
                currentUser: {
                    userId: userId,
                    roles: userRole,
                    permissions: userPermission
                }
            };
            $cookieStore.put('globals', this.globals);
        };
        this.destroy = function () {
            this.globals = {
                currentUser: {
                    userId: null,
                    roles: null,
                    permissions: null
                }
            };
            $cookieStore.put('globals', this.globals);
        };

        return this;
    }])

    .factory('AuthService', ["$http", "Session", "security.login.url", '$cookieStore', '$location', function ($http, Session, loginUrl, $cookieStore, $location) {
        return {
            login: function (credentials) {
                return $http
                    .post(loginUrl, credentials)
                    .then(function (res) {
                        Session.create(res.data.userId, res.data.roles, res.data.permissions);
                    });
            },
            logout: function () {
                Session.destroy();
                $location.path("/home");
            },
            isAuthenticated: function () {
                if ($cookieStore.get('globals') && $cookieStore.get('globals').currentUser) {
                    return !!$cookieStore.get('globals').currentUser.userId;
                }
                return false;
            },
            isAuthorized: function (permission) {
                if ($cookieStore.get('globals') && $cookieStore.get('globals').currentUser) {
                    var permissions = $cookieStore.get('globals').currentUser.permissions;
                    if (!angular.isArray(permissions)) {
                        permissions = [permissions];
                    }
                    return (this.isAuthenticated() &&
                    permissions.indexOf(permission) !== -1);
                }
                return false;
            }
        };
    }])

    .run(function ($rootScope, AUTH_EVENTS, AuthService, $location) {
        // $stateChangeStart if using ui-router (anglar-ui)
        $rootScope.$on('$routeChangeStart', function (event, next) {
            console.log(next);
            if (typeof next.data === 'undefined' || typeof next.data.permission === 'undefined') {
                return;
            }
            var permission = next.data.permission;
            if (!AuthService.isAuthorized(permission)) {
                event.preventDefault();
                if (AuthService.isAuthenticated()) {
                    // user is not allowed
                    $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
                } else {
                    $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
                }
            }
        });
    })

    .run(function ($rootScope, AUTH_EVENTS, AuthService, $location) {
        // $stateChangeStart if using ui-router (anglar-ui)
        $rootScope.$on(AUTH_EVENTS.notAuthenticated, function (event) {
            $location.path("/home");
        });

        $rootScope.$on(AUTH_EVENTS.loginSuccess, function (event) {
            $location.path("/home");
        });
    })

    .config(function ($httpProvider) {
        $httpProvider.interceptors.push([
            '$injector',
            function ($injector) {
                return $injector.get('AuthInterceptor');
            }
        ]);
    })

    .factory('AuthInterceptor', function ($rootScope, $q,
                                          AUTH_EVENTS) {
        return {
            responseError: function (response) {
                alert(response.status);
                if (response.status === 401) {
                    $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated,
                        response);
                }
                if (response.status === 403) {
                    $rootScope.$broadcast(AUTH_EVENTS.notAuthorized,
                        response);
                }
                if (response.status === 419 || response.status === 440) {
                    $rootScope.$broadcast(AUTH_EVENTS.sessionTimeout,
                        response);
                }
                return $q.reject(response);
            }
        };
    })


    .controller('loginController', ['$scope', '$rootScope', 'AUTH_EVENTS', 'AuthService', function ($scope, $rootScope, AUTH_EVENTS, AuthService) {
        $scope.login = function () {
            AuthService.login($scope.credentials).then(function () {
                $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
            }, function () {
                $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
            });
        };
    }]);
