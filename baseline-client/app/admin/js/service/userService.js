/**
 * Created by ucmed on 2017/3/24.
 */
baselineAdmin.factory('userService', ['$q', '$location', '$http', 'baseService','admin.user.url', function ($q, $location, $http, baseService,userUrl) {
    return {
        userAdd: function (user) {
            return baseService.post(userUrl + '/user/add', user);
        },
        getUserList: function (params) {
            return baseService.get(userUrl+'/user/list',params);
        },
        search: function (params) {
            return baseService.get(userUrl+'/user/search',params);
        },
        userRole: function (user) {
            return baseService.post(userUrl + '/user/role', user);
        },
        insertUpdateRole: function (user) {
            return baseService.post(userUrl + '/user/allocate/role', user);
        },
        userUpdate: function (user) {
            return baseService.post(userUrl + '/user/update', user);
        }
    }
}])