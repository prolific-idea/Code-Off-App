(function () {
    angular.module("codeOffChallengeAdmin", [
        "ngResource",
        "ngMessages",
        "codeOffChallengeAdmin.resources",
        "codeOffChallengeAdmin.challenge",
        "codeOffChallengeAdmin.createChallenge",
        "codeOffChallengeAdmin.viewChallenges",
        "codeOffChallengeAdmin.customNgChange"
    ]);

    angular.module("codeOffChallengeAdmin.challenge", ["codeOffChallengeAdmin.resources"]);
})();