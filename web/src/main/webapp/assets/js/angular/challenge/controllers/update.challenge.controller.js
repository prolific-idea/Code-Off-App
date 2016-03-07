(function () {
    var app = angular.module("codeOffChallengeAdmin.challenge");
    app.controller("updateChallengeController", function ($scope, $http, $log, Challenges, SharedUpdateChallenge) {
        var ctrl = $scope;
        ctrl.DirtyForm = false;
        ctrl.$watch(function () {
            return SharedUpdateChallenge.challengeToBeUpdated;
        }, function () {
            console.log("Hello world!");
            ctrl.challenge = SharedUpdateChallenge.getChallengeData();
        });

        ctrl.challenge = SharedUpdateChallenge.getChallengeData();

        ctrl.getTheFiles = function (event) {
            var files = event.target.files;
            if (files.length > 0) {
                var fileToUpload = files[0];
                var fileReader = new FileReader();

                fileReader.onload = function (fileLoadedEvent) {
                    ctrl.challenge.solution = fileLoadedEvent.target.result.split(",")[1];
                };
                fileReader.readAsDataURL(fileToUpload);
            }
        };

        ctrl.Update = function () {
            Challenges
                .update(ctrl.challenge)
                .$promise
                .then(
                    function () {
                        ctrl.updateNotification = true;
                        ctrl.DirtyForm = true;
                    },
                    function () {
                        $log.error;
                    }
                );
        };

        ctrl.Cancel = function () {
            ctrl.challenge = null;
            SharedUpdateChallenge.setChallengeData(null);
        };
        ctrl.OK = function () {
            ctrl.updateNotification = false;
            ctrl.DirtyForm = false;
            ctrl.Cancel();
        }
        ctrl.updateNotification = false;
    });
})();