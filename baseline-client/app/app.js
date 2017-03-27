/**
 * Main application features
 */
var baselineAdmin = angular.module("baselineAdmin", ["ngResource", "ngRoute", 'ui.router.state'])
    .value("admin.user.url", "http://localhost:8070/rest/organization/list")
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
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: "admin/pages/wellcome.html",
                controller: "wellcomeController"
            })
            .when('/login', {
                templateUrl: "admin/pages/login.html",
                controller: "loginController"
            })
            .when('/register', {
                templateUrl: "admin/pages/register.html",
                controller: "registerController"
            })
            .otherwise({
                redirectTo: '/'

            });
    }])
    .run(['$rootScope', '$location', '$http',
        function ($rootScope, $location, $http) {
            // keep user logged in after page refresh
            $rootScope.isLogin = false;
        }
    ]);