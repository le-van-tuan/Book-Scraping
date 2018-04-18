'use strict';
bookScrappingApp.controller('BookScrappingController', ['$scope', 'BookScrappingService', function ($scope, BookScrappingService) {

    var self = this;

    /*Download status*/
    self.waiting = "waiting";
    self.loading = "loading";
    self.error = 'error';
    self.done = 'done';

    self.testJsoup = testJsoup;

    function testJsoup(link) {
        console.info('here is link : ' + link);
        BookScrappingService.testing();
    }
}]);