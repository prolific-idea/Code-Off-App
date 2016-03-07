(function () {
    var app = angular.module("codeOffChallengeAdmin.challenge");
    app.controller("viewChallengesController", function ($scope, $http, $log, Challenges) {
        var ctrl = $scope;
        ctrl.page = 0;
        ctrl.challengesTemp = Challenges.getAllWithPage({ page: ctrl.page, size: 50 });
        ctrl.challengesTemp.$promise.then(function () {
            ctrl.challenges = ctrl.challengesTemp;
            if (ctrl.challenges.length < 50)
                ctrl.CanNotNext = true;
        }, $log.error);

        ctrl.UpdateOrder = function (index) {
        }

        ctrl.OK = function () {
            ctrl.ShowNotification = false;
            ctrl.processedNotification = false;
            ctrl.challengesTemp = Challenges.getAllWithPage({ page: ctrl.page, size: 50 });
            ctrl.challengesTemp.$promise.then(function () {
                ctrl.challenges = ctrl.challengesTemp;
                if (ctrl.challenges.length < 50)
                    ctrl.CanNotNext = true;
            }, $log.error);
        }

        ctrl.ToNextPage = function () {
            ctrl.page += 1;
            ctrl.challengesTemp = Challenges.getAllWithPage({ page: ctrl.page, size: 50 });
            ctrl.challengesTemp.$promise.then(function () {
                ctrl.challenges = ctrl.challengesTemp;
                if (ctrl.challenges.length < 50)
                    ctrl.CanNotNext = true;
            }, $log.error);
        }

        ctrl.ToPrevPage = function () {
            if (ctrl.page != 0) {
                ctrl.page -= 1;
                ctrl.challengesTemp = Challenges.getAllWithPage({ page: ctrl.page, size: 50 });
                ctrl.challengesTemp.$promise.then(function () {
                    ctrl.challenges = ctrl.challengesTemp;
                    if (ctrl.challenges.length < 50)
                        ctrl.CanNotNext = true;
                }, $log.error);
            }
        }
    });
})();