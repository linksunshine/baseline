/**
 * Created by ucmed on 2017/3/24.
 */
baselineAdmin.factory('roleService', ['$q', '$location', '$http', 'baseService', 'admin.user.url', function ($q, $location, $http, baseService, userUrl) {
    return {
        roleAdd: function (role) {
            return baseService.post(userUrl + '/role/add', role);
        },
        getRoleList: function (params) {
            return baseService.get(userUrl + '/role/list', params);
        },
        search: function (params) {
            return baseService.get(userUrl+'/role/search',params);
        },
        rolePermission: function (role) {
            return baseService.post(userUrl + '/role/permission', role);
        },
        insertUpdatePermission: function (role) {
            return baseService.post(userUrl + '/role/allocate/permission', role);
        },
        roleUpdate: function (role) {
            return baseService.post(userUrl + '/role/update', role);
        }
    }
}])