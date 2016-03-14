(function () {
    angular.module("codeOffChallengeAdmin.resources", ["ngCookies"])
        .factory("Challenges", function ($resource, $cookies) {
            return $resource("/api/challenges/:id", null, {
                getAll: {
                    method: "GET",
                    isArray: true,
                    headers: {
                        'x-auth-token': $cookies.get("prolific-login-token")
                    }
                },
                getById: {
                    method: "GET",
                    params: {id: "@id"},
                    headers: {
                        'x-auth-token': $cookies.get("prolific-login-token")
                    }
                },
                getAllWithPage: {
                    method: "GET",
                    params: {
                        pageNum: "@page",
                        pageSize: "@size"
                    },
                    isArray: true,
                    headers: {
                        'x-auth-token': $cookies.get("prolific-login-token")
                    }
                },
                update: {
                    method: "PUT",
                    headers: {
                        'x-auth-token': $cookies.get("prolific-login-token")
                    }
                },
                remove: {
                    method: "DELETE",
                    params: {id: "@id"},
                    headers: {
                        'x-auth-token': $cookies.get("prolific-login-token")
                    }
                },
                create: {
                    method: "POST",
                    headers: {
                        'x-auth-token': $cookies.get("prolific-login-token")
                    }
                }
            });
        }).factory("challengeCount", function ($resource, $cookies) {
        return $resource("/api/challenges/count", null, {
            count: {
                method: "GET",
                headers: {
                    'x-auth-token': $cookies.get("prolific-login-token")
                }
            }
        })
    });
})();