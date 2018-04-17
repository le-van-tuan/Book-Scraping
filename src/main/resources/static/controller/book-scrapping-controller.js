'use strict';
bookScrappingApp.controller('BookScrappingController', ['$scope', 'BookScrappingService', function ($scope, BookScrappingService) {

    var self = this;

    self.testJsoup = testJsoup;

    function testJsoup() {
        console.info('Hello Tuan');
    }
}]);