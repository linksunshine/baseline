/**
 * Created by ucmed on 2017/3/24.
 */
baselineAdmin.factory("baseService",['$http',function($http){
    return{
        post : function(url,params){
            return $http.post(url,params);
        },
        delete : function(url,params){
            var paramsBody = {};
            paramsBody.random = Math.random();
            paramsBody.timestamp = new Date();
            paramsBody.body = params;
            return $http.post(url,paramsBody);
        },
        put : function(url,params){
            var paramsBody = {};
            paramsBody.random = Math.random();
            paramsBody.timestamp = new Date();
            paramsBody.body = params;
            return $http.put(url,paramsBody);
        },
        get : function(url,params){
            return $http.get(url,{params : params});
        }
    }
}]);