(function () {
    var app = angular.module("codeOffChallengeAdmin.challenge");
    app.controller("viewChallengesController", function ($scope, $http, $log, Challenges, challengeCount, SharedUpdateChallenge) {
        var ctrl = $scope;
        var pageSize = 10;
        ctrl.page = 1;
        ctrl.CanNotNext = false;
        ctrl.ShowNotice = false;
        ctrl.successNotification = false;
        ctrl.errorNotification = false;

        ctrl.challengeCountTemp = challengeCount.count();
        ctrl.challengeCountTemp.$promise.then(function () {
            ctrl.challengeCount = ctrl.challengeCountTemp;
        }, $log.error);

        function GetChallenges() {
            ctrl.challengesTemp = Challenges.getAllWithPage({pageNum: ctrl.page, pageSize: pageSize});
            ctrl.challengesTemp.$promise.then(function () {
                ctrl.challenges = ctrl.challengesTemp;
                var lastPage = Math.ceil(ctrl.challengeCount.countOfChallenges / pageSize);
                if (lastPage === ctrl.page)
                    ctrl.CanNotNext = true;
                else
                    ctrl.CanNotNext = false;
            }, $log.error);
        }


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
                var lastPage = Math.ceil(ctrl.challengeCount.countOfChallenges / pageSize);
                if (lastPage === ctrl.page)
                    ctrl.CanNotNext = true;
                else
                    ctrl.CanNotNext = false;
            }, $log.error);
        };

        ctrl.ToNextPage = function () {
            ctrl.page += 1;
            GetChallenges();
        };

        ctrl.ToPrevPage = function () {
            if (ctrl.page != 1) {
                ctrl.page -= 1;
                GetChallenges();
            }
        };

        ctrl.Delete = function (index) {
            ctrl.ShowNotice = true;
            ctrl.challengeToDelete = ctrl.challenges[index];
            Challenges
                .remove({id: ctrl.challengeToDelete.challengeId})
                .$promise
                .then(
                    function () {
                        ctrl.successNotification = true;
                    }, function () {
                        $log.error;
                        ctrl.errorNotification = true;
                    }
                );
        };

        ctrl.OK = function () {
            ctrl.ShowNotice = false;
            ctrl.successNotification = false;
            ctrl.errorNotification = false;
            ctrl.challengeToDelete = {};
            GetChallenges();
        };

        GetChallenges();

        ctrl.$watch(function () {
            return ctrl.SharedData.callGetChallenges;
        }, function () {
            if (ctrl.SharedData.callGetChallenges) {
                GetChallenges();
                ctrl.SharedData.callGetChallenges = false;
            }
        })
    });
})();