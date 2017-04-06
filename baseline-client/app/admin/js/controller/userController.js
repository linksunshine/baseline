/**
 * Created by ucmed on 2017/3/24.
 */
baselineAdmin.controller('userController', ['$scope', 'ngDialog', '$route', '$location', '$state', 'userService', function ($scope, ngDialog, $route, $location, $state, userService) {

    /**
     * 分页获取用户列表
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
    if ($location.path() == '/user') {
        $scope.paginationConf = {
            currentPage: 1,
            itemsPerPage: 10,
            pagesLength: 15,
            perPageOptions: [10, 20, 30, 40, 50],
            onChange: function () {
                var params = {pageNo: $scope.paginationConf.currentPage, pageSize: $scope.paginationConf.itemsPerPage};
                userService.getUserList(params)
                    .success(function (data) {
                        if (data.status == 200) {
                            $scope.userList = data.data;
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
        userService.search(params)
            .success(function (data) {
                if (data.status == 200) {
                    $scope.userList = data.data;
                    $scope.paginationConf.totalItems = data.page.totalRecord;
                }
            });
    };


    //新加用户
    $scope.userAdd = function () {
        ngDialog.openConfirm({
            template: 'admin/pages/templates/userAdd.html',
            className: 'ngdialog-theme-default'
        }).then(function (value) {
            userService.userAdd(value);
            $state.go('user', {}, {
                reload: true
            });
        }, function (reason) {
        });
    };


    //分配角色
    $scope.roleAllocate = function (user) {

        userService.userRole(user)
            .success(function (data) {
                ngDialog.openConfirm({
                    template: 'admin/pages/templates/roleAllocate.html',
                    className: 'ngdialog-theme-default',
                    controller: ['$scope', function ($scope) {
                        $scope.data = data;
                    }]
                }).then(function (value) {
                    userService.insertUpdateRole(value);
                    $state.go('user', {}, {
                        reload: true
                    });
                }, function (reason) {
                });

            });
    };


    //更新用户
    $scope.userUpdate = function (user) {
        ngDialog.openConfirm({
            template: 'admin/pages/templates/userUpdate.html',
            className: 'ngdialog-theme-default',
            controller: ['$scope', function ($scope) {
                $scope.data = user;
            }]
        }).then(function (value) {
            userService.userUpdate(value);
            $state.go('user', {}, {
                reload: true
            });
        }, function (reason) {
        });
    };


}]);