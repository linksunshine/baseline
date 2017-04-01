/**
 * Created by ucmed on 2017/3/24.
 */
baselineAdmin.factory('userService', ['$q', '$location', '$http', 'baseService','admin.user.url', function ($q, $location, $http, baseService,userUrl) {
    return {
        getUserList: function () {
            return baseService.get(userUrl+'/user/list',{});
        }
    }
}])