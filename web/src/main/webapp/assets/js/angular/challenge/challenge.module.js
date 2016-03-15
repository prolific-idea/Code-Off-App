(function () {
    "user strict";

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
        "codeOffChallengeAdmin.updateChallenge",
        "ngCookies"
    ]);

    /*
     $cookies.put("token", response.headers["x-auth-token"]);
     */

    angular.module("codeOffChallengeAdmin.challenge", [
        "codeOffChallengeAdmin.resources",
        "codeOffChallengeAdmin.updateFactory",
        "ui.bootstrap",
        "ngCookies"
    ]);
})();