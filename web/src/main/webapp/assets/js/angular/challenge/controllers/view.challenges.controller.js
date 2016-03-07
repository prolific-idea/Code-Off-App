(function () {
    var app = angular.module("codeOffChallengeAdmin.challenge");
    app.controller("viewChallengesController", function ($scope, $http, $log, Challenges, SharedUpdateChallenge) {
        var ctrl = $scope;
        var pageSize = 6;
        ctrl.page = 0;
        ctrl.challengesTemp = Challenges.getAllWithPage({ page: ctrl.page, size: pageSize });
        ctrl.challengesTemp.$promise.then(function () {
            ctrl.challenges = ctrl.challengesTemp;
            if (ctrl.challenges.length <= pageSize)
                ctrl.CanNotNext = true;
        }, $log.error);

        ctrl.Update = function (index) {
            SharedUpdateChallenge.setChallengeData(ctrl.challenges[index]);
            ctrl.challengeToUpdate = SharedUpdateChallenge.getChallengeData();
            console.log(ctrl.challenges[index]);
        }

        ctrl.OK = function () {
            ctrl.ShowNotification = false;
            ctrl.processedNotification = false;
            ctrl.challengesTemp = Challenges.getAllWithPage({ page: ctrl.page, size: pageSize });
            ctrl.challengesTemp.$promise.then(function () {
                ctrl.challenges = ctrl.challengesTemp;
                if (ctrl.challenges.length <= pageSize)
                    ctrl.CanNotNext = true;
            }, $log.error);
        }

        ctrl.ToNextPage = function () {
            ctrl.page += 1;
            ctrl.challengesTemp = Challenges.getAllWithPage({ page: ctrl.page, size: pageSize });
            ctrl.challengesTemp.$promise.then(function () {
                ctrl.challenges = ctrl.challengesTemp;
                if (ctrl.challenges.length <= pageSize)
                    ctrl.CanNotNext = true;
            }, $log.error);
        }

        ctrl.ToPrevPage = function () {
            if (ctrl.page != 0) {
                ctrl.page -= 1;
                ctrl.challengesTemp = Challenges.getAllWithPage({ page: ctrl.page, size: pageSize });
                ctrl.challengesTemp.$promise.then(function () {
                    ctrl.challenges = ctrl.challengesTemp;
                    if (ctrl.challenges.length <= pageSize)
                        ctrl.CanNotNext = true;
                }, $log.error);
            }
        }
    });
})();