(function () {
    var app = angular.module("codeOffChallengeAdmin.challenge");
    app.controller("createChallengeController", function ($scope, $log, Challenges) {
        var ctrl = $scope;

        ctrl.getTheFiles = function (event) {
            var files = event.target.files;
            if (files.length > 0) {
                var fileToUpload = files[0];
                console.log(fileToUpload);
                var fileReader = new FileReader();

                fileReader.onload = function (fileLoadedEvent) {
                    ctrl.challenge.solution = fileLoadedEvent.target.result;
                    console.log(ctrl.challenge.solution);
                };
                fileReader.readAsDataURL(fileToUpload);
            }
        };

        ctrl.Create = function () {
            ctrl.DirtyForm = true;
            Challenges.create(ctrl.challenge).$promise.then(function () {
                ctrl.createNotification = true
            }, $log.error);
        }

        ctrl.challenge = {
            solution: null,
            url: null,
            solutionFilePath: null,
            startDate: null,
            endDate: null,
            numberOfLinesToCompare: null
        }

        ctrl.OK = function () {
            ctrl.DirtyForm = false;
            ctrl.createNotification = false;
            ctrl.challenge = null;
        }

        ctrl.DirtyForm = false;
    });
})();