(function () {
    "use strict";
    var app = angular.module("codeOffChallengeAdmin.challenge");
    app.controller("container", function ($scope, $cookies, $window) {
        var ctrl = $scope;
        if ($cookies.get("XSRF-TOKEN") === null || $cookies.get("XSRF-TOKEN") === undefined) {
            $window.location.href = "/assets/js/angular/login/login.html";
        } else {
            console.log("User logged in.");
        }
        ctrl.SharedData = {};
        ctrl.SharedData.callGetChallenges = false;
        ctrl.RemoveCookie = function () {
            $cookies.remove("XSRF-TOKEN", {path: "/"});
            $window.location.href = "/assets/js/angular/login/login.html";
        };
    });
})();
