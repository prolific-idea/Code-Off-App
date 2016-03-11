(function () {
    angular.module("codeOffLogin", [
        "ngResource",
        "ngMessages",
        "ui.bootstrap",
        "codeOffLogin.loginView",
        "codeOffLogin.controllers",
        "codeOffLogin.resources"
    ]);

    angular.module("codeOffLogin.controllers", [
        "ngResource",
        "ngMessages",
        "ui.bootstrap",
    ]);
})();