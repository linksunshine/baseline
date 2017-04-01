/**
 * Main application features
 */
var baselineAdmin = angular.module("baselineAdmin", [ 'ngCookies', "ngResource", "ngRoute", 'ui.router.state','security','ngDialog'])
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
                if (response.data.status == status.ERROR) {//ÏµÍ³´íÎó
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
    .config(['$routeProvider', 'USER_PERMISSION', function ($routeProvider, USER_PERMISSION) {
        $routeProvider
            .when('/', {
                templateUrl: "admin/pages/wellcome.html",
                controller: "wellcomeController",
                data: {
                    permission: USER_PERMISSION.PROJECT_VIEW
                }
            })
            .when('/user', {
                templateUrl: "admin/pages/user.html",
                controller: "userController",
                data: {
                    public: true,
                    permission: USER_PERMISSION.USER_VIEW
                }
            })
            .when('/role', {
                templateUrl: "admin/pages/role.html",
                controller: "roleController",
                data: {
                    public: true,
                    permission: USER_PERMISSION.USER_VIEW
                }
            })
            .when('/permission', {
                templateUrl: "admin/pages/permission.html",
                controller: "permissionController",
                data: {
                    public: true,
                    permission: USER_PERMISSION.USER_VIEW
                }
            })
            .otherwise({
                redirectTo: '/'

            });
    }])
    .run(['$rootScope', '$location', '$cookieStore', 'USER_PERMISSION', 'AuthService', '$http',
        function ($rootScope, $location, $cookieStore, USER_PERMISSION, AuthService, $http) {
            $rootScope.userPermissions = USER_PERMISSION;
            $rootScope.isAuthorized = AuthService.isAuthorized;
            $rootScope.isAuthenticated = AuthService.isAuthenticated;
            $rootScope.logout = AuthService.logout;

        }
    ]);
