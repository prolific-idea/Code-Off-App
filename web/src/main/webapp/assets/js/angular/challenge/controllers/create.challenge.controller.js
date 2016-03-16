(function () {
    var app = angular.module("codeOffChallengeAdmin.challenge");
    app.controller("createChallengeController", function ($scope, $cookies, $log, $window, Challenges, SharedUpdateChallenge) {
        if ($cookies.get("XSRF-TOKEN") === null || $cookies.get("XSRF-TOKEN") === undefined) {
            $window.location.href = "/assets/js/angular/login/login.html";
        } else {
            console.log("User logged in.");
        }

        var ctrl = $scope;

        $scope.format = 'yyyy/MM/dd';

        $scope.startDatePopup = {
            opened: false
        };

        $scope.modelOptions = {
            timezone: '+0200'
        };

        $scope.openStartDate = function () {
            $scope.startDatePopup.opened = true;
        };
        ctrl.$watch(function () {
            return SharedUpdateChallenge.challengeToBeUpdated;
        }, function () {
            if (SharedUpdateChallenge.getChallengeData() != null)
                $scope.update = true;
            else
                $scope.update = false;
        });

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

        ctrl.setStartTime = function () {
            ctrl.challenge.startDate.setHours(ctrl.startTime.getHours());
            ctrl.challenge.startDate.setMinutes(ctrl.startTime.getMinutes());
        };

        ctrl.setEndTime = function () {
            ctrl.challenge.endDate.setHours(ctrl.endTime.getHours());
            ctrl.challenge.endDate.setMinutes(ctrl.endTime.getMinutes());
        };

        ctrl.Create = function () {
            ctrl.DirtyForm = true;
            if (ctrl.challenge.startDate > ctrl.challenge.endDate) {
                ctrl.errorNotification = true;
                $log.error("Start date should be before end date.");
                return;
            }

            if (ctrl.challenge.solution == null) {
                ctrl.errorNotification = true;
                $log.error("Please select an file and try again.");
                return
            }

            Challenges.create(ctrl.challenge).$promise.then(function () {
                ctrl.createNotification = true;
            }, function () {
                ctrl.errorNotification = true;
                $log.error;
            });
        };

        ctrl.challenge = {
            solution: null,
            url: null,
            solutionFilePath: null,
            startDate: null,
            endDate: null,
            numberOfLinesToCompare: null
        };

        ctrl.OK = function () {
            ctrl.DirtyForm = false;
            ctrl.createNotification = false;
            ctrl.errorNotification = false;
            ctrl.challenge = null;
        };

        ctrl.OKError = function () {
            ctrl.DirtyForm = false;
            ctrl.createNotification = false;
            ctrl.errorNotification = false;
        }

        ctrl.DirtyForm = false;
    });
})();