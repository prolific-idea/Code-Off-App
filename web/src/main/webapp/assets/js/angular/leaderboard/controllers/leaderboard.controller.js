(function () {
    var app = angular.module("codeOffLeaderboard");
    app.controller("viewLeaderboardController", function ($scope, $http, $log, Coders) {
        var ctrl = $scope;
        var pageSize = 6;
        ctrl.pageNum = 1;
        ctrl.codersTemp = Coders.getPagedLeaderboard({ pageNum: ctrl.pageNum, pageSize: pageSize });
        ctrl.codersTemp.$promise.then(function () {
            ctrl.coders = ctrl.codersTemp;
            if (ctrl.coders.length <= pageSize)
                ctrl.CanNotNext = true;
        }, $log.error);

        ctrl.ToNextPage = function () {
            ctrl.pageNum += 1;
            ctrl.codersTemp = Coders.getPagedLeaderboard({ pageNum: ctrl.pageNum, pageSize: pageSize });
            ctrl.codersTemp.$promise.then(function () {
                ctrl.coders = ctrl.codersTemp;
                if (ctrl.coders.length <= pageSize)
                    ctrl.CanNotNext = true;
            }, $log.error);
        }

        ctrl.ToPrevPage = function () {
            if (ctrl.pageNum != 1) {
                ctrl.pageNum -= 1;
                ctrl.codersTemp = Coders.getPagedLeaderboard({ pageNum: ctrl.pageNum, pageSize: pageSize });
                ctrl.codersTemp.$promise.then(function () {
                    ctrl.coders = ctrl.codersTemp;
                    if (ctrl.coders.length <= pageSize)
                        ctrl.CanNotNext = true;
                }, $log.error);
            }
        }
    });
})();