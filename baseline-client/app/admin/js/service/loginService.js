/**
 * Created by ucmed on 2017/3/24.
 */
baselineAdmin.factory('loginService',['$q','$location','$http','baseService',function($q,$location,$http,baseService){
/*    var loginInfo = {hasLogin:false};
    return {
        login:function(condition){
            var deferred = $q.defer();
            baseService.post('/ajax/login', condition).then(function (obj) {
                if(obj.data.status == Status.SUCCESS) {
                    loginInfo = obj.data.data;
                    loginInfo.hasLogin = true;
                    loginInfo.loginerror = false;
                    $.cookie('userId',loginInfo.userId,{expires: 1});
                    $.cookie('nickName',loginInfo.nickName,{expires: 1});
                    $.cookie('token',loginInfo.token,{expires: 1});
                    $http.defaults.headers.common.token = loginInfo.token;
                }else{
                    loginInfo.hasLogin = false;
                    loginInfo.loginerror = true;
                    loginInfo.loginerroinfo = obj.data.msg;
                }
                deferred.resolve(loginInfo);
            });
            return deferred.promise;
        },
        logOff : function(){
            baseService.post('/ajax/logoff').then(function(obj){
                if(obj.data.status==Status.SUCCESS){
                    $.cookie('token', '', { expires: -1 });
                    $.cookie('userId', '', { expires: -1 });
                    $.cookie('nickName', '', { expires: -1 });
                    location.href = '/my'
                }
            });
        },
        getLoginInfo:function(){
            if($.cookie('token') != null){
                loginInfo.nickName = $.cookie('nickName');
                loginInfo.userId = $.cookie('userId');
                loginInfo.hasLogin = true;
                loginInfo.loginerror = false;
            }else{
                $location.path('login');
            }
            return loginInfo;
        },
        autoLogin : function(){
            //�Զ���½��֤
            var deferred = $q.defer();
            baseService.post('/ajax/autoLogin').then(function(obj){
                if(obj.data.status == Status.SUCCESS){
                    loginInfo = obj.data;
                    loginInfo.hasLogin = true;
                } else{
                    loginInfo.hasLogin = false;
                    loginInfo.nickName = '';
                    $.cookie('token','',{expires: -1});
                }
                deferred.resolve(loginInfo);
            });
            return deferred.promise;
        }
    }*/
}])