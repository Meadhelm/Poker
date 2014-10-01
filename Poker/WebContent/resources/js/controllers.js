(function(angular) {
	
	function ApiController($scope, $http) {
		$http.get('http://localhost:8080/poker/v1/features').success(
				function(data) {
					$scope.data = data;
				});
		$scope.pretty = function (str) {
			return JSON.stringify(JSON.parse(str), undefined, 2);
		}
	}
	;
	var app = angular.module('app', []);
	app.controller('ApiController', [ '$scope', '$http', ApiController ]);

})(angular);