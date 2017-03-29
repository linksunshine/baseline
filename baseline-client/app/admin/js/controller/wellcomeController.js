/**
 * Created by ucmed on 2017/3/24.
 */
baselineAdmin.controller('wellcomeController',['$scope','$location',function($scope,$location){
    console.log('2'+$location.path());
/*    var form = {};
    $scope.form = form;

    $scope.login = function(isValid){
        if(isValid) {
            loginService.login($scope.form).then(function(data){
                if(data.hasLogin){
                    $location.path("my");
                }else{
                    $scope.loginInfo = data;
                }
            });
        }else{
            angular.forEach($scope.loginForm,function(e){
                if(typeof(e) == 'object' && typeof(e.$dirty) == 'boolean'){
                    e.$dirty = true;
                }
            });
        }
    };*/
}])