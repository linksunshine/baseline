/**
 * Created by ucmed on 2017/3/24.
 */
baselineAdmin.controller('userController', ['$scope', '$location', 'userService', function ($scope, $location, userService) {
    $scope.userList = [];
    userService.getUserList()
        .success(function (data) {
            $scope.userList = data;
        });

}]);