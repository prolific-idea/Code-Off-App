(function () {
    angular.module("codeOffChallengeAdmin.resources", [])
        .factory("Challenges", function ($resource) {
            return $resource("/api/challenges/:id", null, {
                getAll: {
                    method: "GET",
                    isArray: true
                },
                getById: {
                    method: "GET",
                    params: {id: "@id"}
                },
                getAllWithPage: {
                    method: "GET",
                    params: {
                        pageNum: "@page",
                        pageSize: "@size"
                    },
                    isArray: true
                },
                update: {
                    method: "PUT"
                },
                remove: {
                    method: "DELETE",
                    params: {id: "@id"}
                },
                create: {
                    method: "POST"
                }
            });
        }).factory("challengeCount", function ($resource) {
        return $resource("/api/challenges/count", null, {
            count: {
                method: "GET"
            }
        })
    });
})();