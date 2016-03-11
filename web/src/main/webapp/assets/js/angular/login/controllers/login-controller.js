(function () {
    var app = angular.module("codeOffLogin.controllers");
    app.controller("loginController", function ($scope, $cookies, $log, LoginResource) {
        var ctrl = $scope;

        ctrl.user = {
            username: "",
            password: ""
        };

        ctrl.login = function () {
            console.log(ctrl.user);
            LoginResource.login(ctrl.user)
                .$promise.then(function (response) {
                if (response.headers["x-auth-token"] != null) {
                    $cookies.put("token", response.headers["x-auth-token"]);
                    console.log($cookies.get("token"));
                }
            }, function () {
                ctrl.errorNotification = true;
                $log.error;
            });
        };

        ctrl.OKError = function () {
            ctrl.errorNotification = false;
        }
    })
})();