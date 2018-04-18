'use strict';
bookScrappingApp.service('BookScrappingService', ['$http', '$q', 'urls' , function ($http, $q, urls) {
    this.downloadBook = function downloadBook(id) {
        var deferred = $q.defer();
        $http.get(urls.DOWNLOAD + id)
            .then(function (response) {
                    deferred.resolve(response);
                },
                function (reason) {
                    deferred.reject(reason);
            });
    }
}]);