'use strict';
bookScrappingApp.factory('BookScrappingService', ['$http', '$q', function ($http, $q) {
    var DOWNLOAD = 'http://localhost:8090/download/';
    var INIT_DATA = 'http://localhost:8090/init-data';

    var factory = {
        downloadBook:downloadBook,
        setupData:initData
    };
    return factory;

    function downloadBook(id) {
        var deferred = $q.defer();
        $http.get(DOWNLOAD + id)
            .then(function (response) {
                    deferred.resolve(response);
                },
                function (reason) {
                    deferred.reject(reason);
            });
    }

    function initData() {
        console.log('initialize data...');
        // var deferred = $q.defer();
        // $http.get(INIT_DATA)
        //     .then(function (value) {
        //             deferred.resolve(value.data);
        //         },
        //         function (reason) {
        //             deferred.reject(reason);
        //         }
        //     );
        // return deferred.promise;
    }
}]);