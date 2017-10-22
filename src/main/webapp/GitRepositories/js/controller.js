var app = angular.module("topInformationApp", []);
app.controller("topInformationCtrl", function($scope, $http) {
	$scope.page = 1;
	$scope.totalPages = 1;
	
	$scope.getRepositories = function(){
		$http({
			method: 'GET',
			url: '/services/gitRepositories/getStarredRepositories/' + $scope.page
		}).then(function successCallback(response) {
			var json = angular.fromJson(response.data);
			$scope.rows = json.items;
			$scope.totalPages = Math.floor((json.totalItems / 30) + 1);
		});
	}
	$scope.getRepositories();
	
	$scope.previousPage = function() {
		if ($scope.page > 1){
			$scope.page--;
		}
		
		$scope.getRepositories();
	}
	
	$scope.nextPage = function() {
		$scope.page++;
		$scope.getRepositories();
	}
		
});