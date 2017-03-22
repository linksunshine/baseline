/**
 * Main application features
 */
var baselineAdmin = angular.module("baselineAdmin", ["ngResource","ngRoute"]);

baselineAdmin.directive("whenScrolled", function() {
    return function(scope, elm, attr) {
        var raw = elm[0];
        elm.bind("scroll", function() {
            if (raw.scrollTop + raw.offsetHeight >= raw.scrollHeight - 1) {
                scope.$apply(attr.whenScrolled);
            }
        });
    };
});

baselineAdmin.value("admin.user.url", "http://localhost:8070/rest/organization/list");

/*baselineAdmin
    .config(function($httpProvider){
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    });*/

baselineAdmin.factory('organizationService', ['$resource', 'admin.user.url', function ($resource, serviceUrl) {
    return $resource('/organization/list', {}, {
        'query': {
            url: serviceUrl,
            method: 'GET',
            isArray: true
        }
    });
}]);

// Main controller for tabs (Users, Roles, Permissions)
baselineAdmin.controller("IndexController",
    ['$scope','$timeout','organizationService', function ($scope,$timeout,organizationService) {
        var page = 0;
        var pageSize = 10;
        $scope.searchField = "";
        $scope.organizations = [];
        $scope.loading = false;
        $scope.waitingForResponse = false;

        $scope.loadMore = function() {
            if (!$scope.waitingForResponse) {
                $scope.waitingForResponse = true;
                // Show ajax after some waiting time
                // This avoid having ajax request status
                // flicker if
                // server answer too fast.
                $timeout(function() {
                    if ($scope.waitingForResponse)
                        $scope.loading = true;
                }, 300);

                organizationService.query({
                }, function(results) {
                    $scope.loading = false;
                    for (var i = 0; i < results.length; i++) {
                        $scope.organizations.push(results[i]);
                    }
                    page = page + 1;
                    $scope.waitingForResponse = false;
                }, function() {
                    $scope.waitingForResponse = false;
                });
            }
        };

        $scope.loadMore();
    }]);