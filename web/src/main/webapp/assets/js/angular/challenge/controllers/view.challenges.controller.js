(function () {
    var app = angular.module("codeOffChallengeAdmin.challenge");
    app.controller("viewChallengesController", function ($scope, $http, $log, Challenges, challengeCount, SharedUpdateChallenge) {
        var ctrl = $scope;
        var pageSize = 6;
        ctrl.page = 1;
        ctrl.CanNotNext = false;

        ctrl.challengeCountTemp = challengeCount.count();
        ctrl.challengeCountTemp.$promise.then(function () {
            ctrl.challengeCount = ctrl.challengeCountTemp;
        }, $log.error);

        ctrl.challengesTemp = Challenges.getAllWithPage({pageNum: ctrl.page, pageSize: pageSize});
        ctrl.challengesTemp.$promise.then(function () {
            ctrl.challenges = ctrl.challengesTemp;
            var lastPage = Math.ceil( ctrl.challengeCount.countOfChallenges / pageSize);
            if (lastPage === ctrl.page)
                ctrl.CanNotNext = true;
            else
                ctrl.CanNotNext = false;
        }, $log.error);

        ctrl.Update = function (index) {
            SharedUpdateChallenge.setChallengeData(ctrl.challenges[index]);
            ctrl.challengeToUpdate = SharedUpdateChallenge.getChallengeData();
            console.log(ctrl.challenges[index]);
        };

        ctrl.OK = function () {
            ctrl.ShowNotification = false;
            ctrl.processedNotification = false;
            ctrl.challengesTemp = Challenges.getAllWithPage({pageNum: ctrl.page, pageSize: pageSize});
            ctrl.challengesTemp.$promise.then(function () {
                ctrl.challenges = ctrl.challengesTemp;
                var lastPage = Math.ceil( ctrl.challengeCount.countOfChallenges / pageSize);
                if (lastPage === ctrl.page)
                    ctrl.CanNotNext = true;
                else
                    ctrl.CanNotNext = false;
            }, $log.error);
        };

        ctrl.ToNextPage = function () {
            ctrl.page += 1;
            ctrl.challengesTemp = Challenges.getAllWithPage({pageNum: ctrl.page, pageSize: pageSize});
            ctrl.challengesTemp.$promise.then(function () {
                ctrl.challenges = ctrl.challengesTemp;
                var lastPage = Math.ceil( ctrl.challengeCount.countOfChallenges / pageSize);
                if (lastPage === ctrl.page)
                    ctrl.CanNotNext = true;
                else
                    ctrl.CanNotNext = false;
            }, $log.error);
        };

        ctrl.ToPrevPage = function () {
            if (ctrl.page != 1) {
                ctrl.page -= 1;
                ctrl.challengesTemp = Challenges.getAllWithPage({pageNum: ctrl.page, pageSize: pageSize});
                ctrl.challengesTemp.$promise.then(function () {
                    ctrl.challenges = ctrl.challengesTemp;
                    var lastPage = Math.ceil( ctrl.challengeCount.countOfChallenges / pageSize);
                    if (lastPage === ctrl.page)
                        ctrl.CanNotNext = true;
                    else
                        ctrl.CanNotNext = false;
                }, $log.error);
            }
        };
    });
})();