(function () {
    angular.module("codeOffLogin.resources", [])
        .factory("LoginResource", function ($resource) {
        return $resource("/api/user/login", null, {
            login: {
                method: "POST",
                transformResponse: function(data, headers){
                    response = {};
                    response.data = data;
                    response.headers = headers();
                    return response;
                }
            }
        })
    });
})();