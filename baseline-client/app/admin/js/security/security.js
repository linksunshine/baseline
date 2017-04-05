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
        PROJECT_VIEW: 'P:1',
        PROJECT_CREATE: 'P:2',
        PROJECT_UPDATE: 'P:4',
        PROJECT_SEARCH: 'P:16',
        PROJECT_SOFTWARE: 'P:32',
        SOFTWARE_VIEW: 'S:1',
        SOFTWARE_CREATE: 'S:2',
        SOFTWARE_UPDATE: 'S:4',
        SOFTWARE_SEARCH: 'S:16',
        SOFTWARE_VERSION: 'S:32',
        VERSION_CREATE: 'V:2',
        VERSION_UPDATE: 'V:4',
        VERSION_UNSHELVE: 'V:32',
        USER_VIEW: 'U:1',
        USER_CREATE: 'U:2',
        USER_UPDATE: 'U:4',
        USER_DELETE: 'U:8',
        USER_SEARCH: 'U:16',
        USER_RESETPSW: 'U:32',
        ROLE_VIEW: 'R:1',
        ROLE_CREATE: 'R:2',
        ROLE_UPDATE: 'R:4',
        ROLE_DELETE: 'R:8',
        ROLE_SEARCH: 'R:16',
        ROLE_PERMISSION: 'R:32',
        ROLE_USER: 'R:64'
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
                $location.path("/");
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
            $location.path("/");
        });

        $rootScope.$on(AUTH_EVENTS.loginSuccess, function (event) {
            $location.path("/");
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
