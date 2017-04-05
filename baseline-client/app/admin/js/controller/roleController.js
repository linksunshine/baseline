/**
 * Created by ucmed on 2017/3/24.
 */
baselineAdmin.controller('roleController', ['$scope', 'ngDialog', '$route','$location', 'roleService', function ($scope, ngDialog, $route,$location, roleService) {


    /**
     * ��ҳ��ȡ��ɫ�б�
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
    if ($location.path() == '/role') {
        $scope.paginationConf = {
            currentPage: 1,
            itemsPerPage: 10,
            pagesLength: 15,
            perPageOptions: [10, 20, 30, 40, 50],
            onChange: function () {
                var params = {pageNo: $scope.paginationConf.currentPage, pageSize: $scope.paginationConf.itemsPerPage};
                roleService.getRoleList(params)
                    .success(function (data) {
                        if (data.status == 200) {
                            $scope.roleList = data.data;
                            $scope.paginationConf.totalItems = data.page.totalRecord;
                        }
                    });
            }
        };
    }


    /**
     * ����
     * @param ev
     */
    $scope.searchKeyDown = function (ev) {
        $scope.paginationConf.currentPage = 1;
        var params = {
            pageNo: $scope.paginationConf.currentPage,
            pageSize: $scope.paginationConf.itemsPerPage,
            searchKey: $scope.search
        };
        roleService.search(params)
            .success(function (data) {
                if (data.status == 200) {
                    $scope.roleList = data.data;
                    $scope.paginationConf.totalItems = data.page.totalRecord;
                }
            });
    };

    //�¼ӽ�ɫ
    $scope.roleAdd = function () {
        ngDialog.openConfirm({
            template: 'admin/pages/templates/roleAdd.html',
            className: 'ngdialog-theme-default'
        }).then(function (value) {
            roleService.roleAdd(value);
            $route.reload();
        }, function (reason) {
        });
    };

    //������Դ
    $scope.permissionAllocate = function (role) {

        roleService.rolePermission(role)
            .success(function (data) {
                ngDialog.openConfirm({
                    template: 'admin/pages/templates/permissionAllocate.html',
                    className: 'ngdialog-theme-default',
                    controller: ['$scope', function ($scope) {
                        $scope.data = data;
                    }]
                }).then(function (value) {
                    roleService.insertUpdatePermission(value);
                    $route.reload();
                }, function (reason) {
                });

            });
    };


    //���½�ɫ
    $scope.roleUpdate = function (role) {
        ngDialog.openConfirm({
            template: 'admin/pages/templates/roleUpdate.html',
            className: 'ngdialog-theme-default',
            controller: ['$scope', function ($scope) {
                $scope.data = role;
            }]
        }).then(function (value) {
            roleService.roleUpdate(value);
            $route.reload();
        }, function (reason) {
        });
    };


}]);