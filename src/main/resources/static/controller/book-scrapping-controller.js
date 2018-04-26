'use strict';
bookScrappingApp.controller('BookScrappingController', ['$scope', 'BookScrappingService', function ($scope, BookScrappingService) {

    var self = this;

    self.linkContents = [];
    self.linkContent = {id : null, linkTitle : '', linkUrl : '', isSubmit : false};

    self.downloadBook = downloadBook;
    self.submit = submit;

    self.initData =  initData;

    function initData() {
        BookScrappingService.setupData();
            // .then(function (value) {
            //     self.linkContents  = value;
            // }, function (reason) {
            //     console.error(reason);
            // });
    }

    function downloadBook(id) {
        console.info('Start download book on link : ' + id);
        BookScrappingService.downloadBook(id);
    }

    function submit(id) {
        for(var i = 0; i < self.linkContents.length;i++){
            if(self.linkContents[i].id === id){
                self.linkContents[i].isSubmit = true;
                break;
            }
        }
    }
}]);