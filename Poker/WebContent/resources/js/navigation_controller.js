(function(angular) {
	angular.module('app').controller('navigation-controller',
			function($scope, $http, $location, $window) {
				var path = $window.location.href;
				$scope.page = {
					home : false,
					register : false,
					profile : false,
					settings : false
				};
				if (path.contains('home.html')) {
					$scope.page.home = true;
				} else if (path.contains('register.html')) {
					$scope.page.register = true;
				} else if (path.contains('profile.html')) {
					$scope.page.profile = true;
				} else if (path.contains('settings.html')) {
					$scope.page.settings = true;
				} else if (path.contains('login.html')) {
					$scope.page.login = true;
				} else if (path.contains('main.html')) {
					$scope.page.main = true;
				}

				$scope.session = $location.search().session;
				$scope.main = function() {
					$window.location.href = 'index.html#?session=test';
				};
				$scope.home = function() {
					$window.location.href = 'home.html#?session=test';
				};
				$scope.profile = function() {
					$window.location.href = 'profile.html#?session=test';
				};
				$scope.settings = function() {
					$window.location.href = 'settings.html#?session=test';
				};
				$scope.register = function() {
					$window.location.href = 'register.html#?session=test';
				};
				$scope.login = function() {
					$window.location.href = 'login.html#?session=test';
				};
			});
})(angular);