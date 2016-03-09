(function () {
    'use strict';

    angular.module("codeOffLeaderboard")
        .factory("Coders", function ($resource) {
            return $resource("/api/leaderboard/board", null, {
                getLeaderboard: {
                    method: "GET",
                    isArray: true
                },
                getPagedLeaderboard: {
                    method: "GET",
                    params: {
                        pageNum: "@page",
                        pageSize: "@size"
                    },
                    isArray: true
                }
            });
        }).factory("PersonCount", function ($resource) {
        return $resource("/api/person/count", null, {
            count: {
                method: "GET"
            }
        });
    });
}());
