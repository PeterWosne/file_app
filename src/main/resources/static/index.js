var myApp = angular.module('myApp', []);

myApp.directive('fileModel', ['$parse', function ($parse) {
            return {
               restrict: 'A',
               link: function(scope, element, attrs) {
                  var model = $parse(attrs.fileModel);
                  var modelSetter = model.assign;

                  element.bind('change', function() {
                     scope.$apply(function() {
                        modelSetter(scope, element[0].files[0]);
                     });
                  });
               }
            };
         }]);


myApp.controller('myCtrl', function($scope, $http) {

   $scope.loadProducts = function() {
         $http.get('http://localhost:8189/app/products')
           .then(function (response) {
              $scope.products = response.data;
           });
   };

   //для создания нового продукта(+image + описание)
   $scope.uploadFile = function() {
      var file = $scope.myFile;
      var uploadUrl = "http://localhost:8189/app/upload";

      var fd = new FormData();
      fd.append('file', file);

      var data = $scope.newProduct.title + ","
       + $scope.newProduct.price + ","
       + $scope.newProduct.year + ","
       + $scope.newProduct.capacity + ","
       + $scope.newProduct.mileage;


      fd.append('data', angular.toJson(data))
      $scope.newProduct = null;

      $http.post(uploadUrl, fd, {
         transformRequest: angular.identity,
         headers: {'Content-Type': undefined}
      }).then(function (response) {
        $scope.loadProducts();
      });
   };

   $scope.deleteProduct = function(id) {
      $http.get('http://localhost:8189/app/products/delete/' + id)
         .then(function (response) {
            $scope.loadProducts();
         });
   };

   $scope.loadProduct = function(id) {
      $http.get('http://localhost:8189/app/products/' + id)
         .then(function (response) {
            $scope.product = response.data;
         });
   };

   $scope.editProduct = function(id) {
      $http.get('http://localhost:8189/app/products/' + id)
           .then(function (response) {
              $scope.editedProduct = response.data;
      });
   };


   //добавление изображения для продукта
   $scope.uploadImageForProduct = function (id) {
         var file = $scope.newFile;
         var uploadUrl = "http://localhost:8189/app/uploadf";

         var fd = new FormData();
         fd.append('file', file);

         var data = id;

         fd.append('data', angular.toJson(data));

         $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
         }).then(function (response) {
           $scope.editProduct(id);
         });
   };

   $scope.deleteFile = function (id, productId) {
      $http.get('http://localhost:8189/app/delete/' + id)
         .then(function (response) {
           $scope.editProduct(productId);
         });
   };

//   save changes
     $scope.saveChanges = function (id) {
        $scope.editedProduct.id = id;
        $http.post('http://localhost:8189/app/products', $scope.editedProduct)
           .then(function (response) {
              $scope.editProduct(productId);
         });
     };

   $scope.loadProducts();
});



