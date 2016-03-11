(function () {
    angular.module("codeOffChallengeAdmin", [
        "ngResource",
        "ngMessages",
        "ui.bootstrap",
        "codeOffChallengeAdmin.navBar",
        "codeOffChallengeAdmin.resources",
        "codeOffChallengeAdmin.updateFactory",
        "codeOffChallengeAdmin.challenge",
        "codeOffChallengeAdmin.createChallenge",
        "codeOffChallengeAdmin.viewChallenges",
        "codeOffChallengeAdmin.customNgChange",
        "codeOffChallengeAdmin.updateChallenge"
    ]);

    angular.module("codeOffChallengeAdmin.challenge", [
        "codeOffChallengeAdmin.resources",
        "codeOffChallengeAdmin.updateFactory",
        "ui.bootstrap"
    ]);
})();