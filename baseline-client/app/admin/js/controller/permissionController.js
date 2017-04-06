/**
 * Created by ucmed on 2017/3/24.
 */
baselineAdmin.controller('permissionController', ['$scope', 'ngDialog', '$route', '$location', '$state', 'permissionService', function ($scope, ngDialog, $route, $location, $state, permissionService) {

    /**
     * 分页获取资源列表
     * currentPage: Current page number, default 1
     * totalItems: Total number of items in all pages, if you want to get totalItems from ajaxRequest,
     *             you can set the totalItems in onChange function;
     *
     * itemsPerPage:  number of items per page, default 15
     * onChange: when the pagination is change, it will excute the function.
     * pagesLength: number for pagination size, default 9
     * perPageOptions: defind select how many items in a page, default [10, 15, 20, 30, 50]
     *
     */
    if ($location.path() == '/permission') {
        $scope.paginationConf = {
            currentPage: 1,
            itemsPerPage: 10,
            pagesLength: 15,
            perPageOptions: [10, 20, 30, 40, 50],
            onChange: function () {
                var params = {pageNo: $scope.paginationConf.currentPage, pageSize: $scope.paginationConf.itemsPerPage};
                permissionService.getPermissionList(params)
                    .success(function (data) {
                        if (data.status == 200) {
                            $scope.permissionList = data.data;
                            $scope.paginationConf.totalItems = data.page.totalRecord;
                        }
                    });
            }
        };
    }


    /**
     * 搜索
     * @param ev
     */
    $scope.searchKeyDown = function (ev) {
        $scope.paginationConf.currentPage = 1;
        var params = {
            pageNo: $scope.paginationConf.currentPage,
            pageSize: $scope.paginationConf.itemsPerPage,
            searchKey: $scope.search
        };
        permissionService.search(params)
            .success(function (data) {
                if (data.status == 200) {
                    $scope.permissionList = data.data;
                    $scope.paginationConf.totalItems = data.page.totalRecord;
                }
            });
    };


    //新加资源
    $scope.permissionAdd = function () {
        ngDialog.openConfirm({
            template: 'admin/pages/templates/permissionAdd.html',
            className: 'ngdialog-theme-default'
        }).then(function (value) {
            permissionService.permissionAdd(value);
            $state.go('permission', {}, {
                reload: true
            });
        }, function (reason) {
        });
    };

    //更新资源
    $scope.permissionUpdate = function (permission) {
        ngDialog.openConfirm({
            template: 'admin/pages/templates/permissionUpdate.html',
            className: 'ngdialog-theme-default',
            controller: ['$scope', function ($scope) {
                $scope.data = permission;
            }]
        }).then(function (value) {
            permissionService.permissionUpdate(value);
            $state.go('permission', {}, {
                reload: true
            });
        }, function (reason) {
        });
    };


}]);