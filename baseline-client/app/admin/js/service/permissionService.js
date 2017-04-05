/**
 * Created by ucmed on 2017/3/24.
 */
baselineAdmin.factory('permissionService', ['$q', '$location', '$http', 'baseService', 'admin.user.url', function ($q, $location, $http, baseService, userUrl) {
    return {
        permissionAdd: function (permission) {
            return baseService.post(userUrl + '/permission/add', permission);
        },
        getPermissionList: function (params) {
            return baseService.get(userUrl + '/permission/list', params);
        },
        search: function (params) {
            return baseService.get(userUrl+'/permission/search',params);
        },
        permissionUpdate: function (permission) {
        return baseService.post(userUrl + '/permission/update', permission);
    }
    }
}])