/**
 * Created by ucmed on 2017/3/24.
 */
baselineAdmin.controller('permissionController',['$scope', '$location','ngDialog','permissionService',function($scope,$location,ngDialog,permissionService){
    //新加资源
    $scope.permissionAdd = function () {
        ngDialog.openConfirm({
            template: 'admin/pages/templates/permissionAdd.html',
            className: 'ngdialog-theme-default'
        }).then(function (value) {
            permissionService.permissionAdd(value);
            $location.path("/permission");
        }, function (reason) {
        });
    };
}]);