(function () {
    angular.module("codeOffLogin.resources", [])
        .factory("challengeCount", function ($resource) {
        return $resource("/api/user/login", null, {
            login: {
                method: "POST"
            }
        })
    });
})();