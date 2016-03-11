(function () {
    var app = angular.module("codeOffLogin.controllers");
    app.controller("loginController", function ($scope, $log, LoginResource) {
        var ctrl = $scope;

        ctrl.user = {
            username:"",
            password: ""
        };

        ctrl.login = function () {
            console.log(ctrl.user);
            LoginResource.login(ctrl.user)
                .$promise.then(function(response){
                console.log(response);
                console.log(response.headers["x-auth-token"]);
            },$log.error);
        }
    })
})();