'use strict';
bookScrappingApp.controller('BookScrappingController', ['$scope', 'BookScrappingService', function ($scope, BookScrappingService) {

    var self = this;

    /*Download status*/
    self.waiting = "waiting";
    self.loading = "loading";
    self.error = 'error';
    self.done = 'done';

    self.downloadBook = downloadBook;

    function downloadBook(id) {
        console.info('Start download book on link : ' + id);
        BookScrappingService.downloadBook(id);
    }
}]);