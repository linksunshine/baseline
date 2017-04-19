/**
 * Main application features
 */
var baselineAdmin = angular.module("baselineAdmin", ['ngCookies', "ngResource", "ngRoute", 'ui.router.state', 'security', 'ngDialog', 'checklist-model', 'tm.pagination', 'ui.router', 'angularUtils.directives.uiBreadcrumbs'])
    .value("security.login.url", "http://localhost:8070/rest/login")
    .value("admin.user.url", "http://localhost:8070/rest")
/**
 * Created by ucmed on 2017/3/24.
 */
/*****
 * Interceptor
 */
    .factory('statusInterceptor', ['$q', '$location', function ($q, $location) {
        var statusInterceptor = {
            request: function (request) {
                // Header - Token
                /*                request.headers = request.headers || {};
                 if ($.cookie('token')) {
                 request.headers.Authorization = 'Bearer ' + $.cookie('token');
                 }*/
                return request;
            },
            response: function (response) {
                var deferred = $q.defer();
                if (response.data.status == status.ERROR) {
                    $location.path('/error');
                    return deferred.promise;
                } else if (response.data.status == status.FAILED) {
                    alert(response.data.msg);
                    return deferred.promise;
                } else if (response.data.status == status.INVALID_TOKEN) {
                    $location.path('/loginCreate');
                    return deferred.promise;
                } else if (response.data.status == status.INVALID_USER) {
                    $location.path('/loginCreate');
                    return deferred.promise;
                } else {
                    return response;
                }
            }
        };
        return statusInterceptor;
    }])
    .config(['$stateProvider', '$urlRouterProvider', 'USER_PERMISSION', function ($stateProvider, $urlRouterProvider, USER_PERMISSION) {
        $stateProvider
            .state('home', {
                url: '^',
                views: {
                    'main@': {
                        templateUrl: 'admin/pages/content/home.html',
                        controller: "homeController"
                    }
                },
                breadcrumb: {
                    displayName: 'Home'
                }
            })
            .state('user', {
                url: '/user',
                views: {
                    'main@': {
                        templateUrl: 'admin/pages/content/user.html',
                        controller: "userController"
                    }
                },
                data: {
                    public: true,
                    permission: USER_PERMISSION.USER_VIEW
                },
                breadcrumb: {
                    displayName: '用户列表'
                }
            })
            .state('role', {
                url: '/role',
                views: {
                    'main@': {
                        templateUrl: 'admin/pages/content/role.html',
                        controller: "roleController"
                    }
                },
                data: {
                    public: true,
                    permission: USER_PERMISSION.ROLE_VIEW
                },
                breadcrumb: {
                    displayName: '角色列表'
                }
            })
            .state('permission', {
                url: '/permission',
                views: {
                    'main@': {
                        templateUrl: 'admin/pages/content/permission.html',
                        controller: "permissionController"
                    }
                },
                data: {
                    public: true,
                    permission: USER_PERMISSION.PERMISSION_VIEW
                },
                breadcrumb: {
                    displayName: '资源列表'
                }
            });

        $urlRouterProvider.otherwise('/home');

    }])
    .run(['$rootScope', '$location', '$cookieStore', 'USER_PERMISSION', 'AuthService', '$http',
        function ($rootScope, $location, $cookieStore, USER_PERMISSION, AuthService, $http) {
            $rootScope.userPermissions = USER_PERMISSION;
            $rootScope.isAuthorized = AuthService.isAuthorized;
            $rootScope.isAuthenticated = AuthService.isAuthenticated;
            $rootScope.logout = AuthService.logout;

        }
    ]);
