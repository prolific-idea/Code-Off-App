(function () {
    var app = angular.module("codeOffLogin.controllers");
    app.controller("loginController", function ($scope, $cookies, $log, $window, LoginResource) {
        var ctrl = $scope;

        ctrl.user = {
            username: "",
            password: ""
        };

        ctrl.login = function () {
            LoginResource.login(ctrl.user)
                .$promise.then(function (response) {
                if (response.headers["x-auth-token"] != null) {
                    $cookies.put("prolific-login-token", response.headers["x-auth-token"], {path: "/"});
                    $window.location.href = "/assets/js/angular/challenge/challengeMain.html";
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