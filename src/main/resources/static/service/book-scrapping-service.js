'use strict';
bookScrappingApp.factory('BookScrappingService', ['$http', '$q', 'urls' , function ($http, $q, urls) {
    var factory = {
        testing : testing
    };
    return factory;

    function testing() {
        var deferred = $q.defer();

        $http.get(urls.TESTING)
            .then(function (response) {
                    deferred.resolve(response);
                },
                function (reason) {
                    deferred.reject(reason);
            });
    }
}]);