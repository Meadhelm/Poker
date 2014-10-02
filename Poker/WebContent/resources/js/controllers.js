(function(angular) {
	
	function ApiController($scope, $http) {
		$http.get('http://192.168.1.131:8080/Poker/v1/features').success(
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