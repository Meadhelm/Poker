(function(angular) {
	angular.module('app').controller(
			'login_controller',
			function($scope, $http, $window) {
				$scope.user = {
					email : '',
					password : ''
				};

				$scope.signIn = function() {
					$http.post('http://localhost:8080/Poker/v1/user/login',
							$scope.user).success(function(data) {
						$scope.data = data;
						$window.location.href = '#home.html?session=test';
						window.location.reload();
					});
				};
			});
})(angular);