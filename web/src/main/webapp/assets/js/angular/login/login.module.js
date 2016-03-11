(function () {
    angular.module("codeOffLogin", [
        "ngResource",
        "ngMessages",
        "ui.bootstrap",
        "codeOffLogin.loginView",
        "codeOffLogin.resources",
        "codeOffLogin.controllers"
    ]);

    angular.module("codeOffLogin.controllers", [
        "ngResource",
        "ngMessages",
        "ui.bootstrap",
        "ngCookies"
    ]);
})();