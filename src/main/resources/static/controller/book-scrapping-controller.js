'use strict';
bookScrappingApp.controller('BookScrappingController', ['$scope', 'BookScrappingService', function ($scope, BookScrappingService) {

    var self = this;

    self.linkContents = [];

    self.downloadBook = downloadBook;
    self.submit = submit;

    initData();

    function initData() {
        console.info('I am came here to love you babe.');
        BookScrappingService.initData()
            .then(function (value) {
                 self.linkContents = value;
            },
                function (reason) {
                console.info('error : '  + reason);
            });
    }

    function downloadBook(id) {
        console.info('Start download book on link : ' + id);
        BookScrappingService.downloadBook(id);
    }

    function submit(id) {
        for(var i = 0; i < self.linkContents.length; i++){
            if(self.linkContents[i].id === id){
                self.linkContents[i].submit = true;
                break;
            }
        }
        downloadBook(id);
    }
}]);