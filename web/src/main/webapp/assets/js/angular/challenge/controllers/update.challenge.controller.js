(function () {
    var app = angular.module("codeOffChallengeAdmin.challenge");
    app.controller("updateChallengeController", function ($scope, $http, $log, $uibModal, Challenges, SharedUpdateChallenge) {
        var ctrl = $scope;

        $scope.format = 'yyyy/MM/dd';

        $scope.startDatePopup = {
            opened: false
        };

        $scope.openStartDate = function () {
            $scope.startDatePopup.opened = true;
        };

        $scope.endDatePopup = {
            opened: false
        };

        $scope.openEndDate = function () {
            $scope.endDatePopup.opened = true;
        };

        $scope.dateOptions = {
            dateDisabled: null,
            formatYear: 'yyyy',
            maxDate: new Date(9999, 12, 31),
            minDate: new Date(),
            startingDay: 1
        };

        ctrl.DirtyForm = false;
        ctrl.$watch(function () {
            return SharedUpdateChallenge.challengeToBeUpdated;
        }, function () {
            if (SharedUpdateChallenge.getChallengeData() != null) {
                ctrl.challenge = SharedUpdateChallenge.getChallengeData();
                ctrl.startTime = ctrl.challenge.startDate.getTime();
                ctrl.endTime = ctrl.challenge.endDate.getTime();
            }
        });

        ctrl.setStartTime = function () {
            ctrl.challenge.startDate.setHours(ctrl.startTime.getHours() + 2);
            ctrl.challenge.startDate.setMinutes(ctrl.startTime.getMinutes());
        };

        ctrl.setEndTime = function () {
            ctrl.challenge.endDate.setHours(ctrl.endTime.getHours());
            ctrl.challenge.endDate.setMinutes(ctrl.endTime.getMinutes());
        };

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
            ctrl.challenge.startDate.setTime(ctrl.startTime);
            ctrl.challenge.endDate.setTime(ctrl.endTime);
            if (ctrl.challenge.startDate > ctrl.challenge.endDate) {
                ctrl.errorNotification = true;
                $log.error("Start date should be before end date.");
                return;
            }
            Challenges
                .update(ctrl.challenge)
                .$promise
                .then(
                    function () {
                        ctrl.updateNotification = true;
                        ctrl.DirtyForm = true;
                        SharedUpdateChallenge.setChallengeData(null);
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
        };

        ctrl.OKError = function () {
            ctrl.DirtyForm = false;
            ctrl.createNotification = false;
            ctrl.errorNotification = false;
        };

        ctrl.updateNotification = false;
    });
})();