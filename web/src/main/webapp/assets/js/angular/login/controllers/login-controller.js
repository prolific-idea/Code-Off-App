(function () {
    var app = angular.module("codeOffLogin.controllers");
    app.controller("loginController", function ($scope, $log, LoginResource) {
        var ctrl = $scope;

        ctrl.user = {
            username:"",
            password: ""
        }
        ctrl.login = function () {
            console.log(user);
            LoginResource.login(user);
        }
    })
})();