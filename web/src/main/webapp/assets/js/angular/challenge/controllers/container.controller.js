(function () {
    "use strict";
    var app = angular.module("codeOffChallengeAdmin.challenge");
    app.controller("container", function ($scope) {
        var ctrl = $scope;
        $scope.SharedData = {};
        $scope.SharedData.callGetChallenges = false;
        
    });
})();
