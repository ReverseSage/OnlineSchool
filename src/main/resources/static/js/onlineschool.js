var userName = "";
function viewUserName()
{
	return userName;
}

(function () {'use strict';

    angular.module('app', ['ngRoute', 'ngCookies'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/signup', {
                controller: 'registerCtrl',
                templateUrl: 'signup.html',
                controllerAs: 'vm'
            })

            .when('/login', {
                controller: 'LoginCtrl',
                templateUrl: 'login.html',
                controllerAs: 'vm'
            })
		
		   .when('/GStudentView', {
                controller: 'studentCtrl',
                templateUrl: 'GStudentView.html',
                controllerAs: 'vm'
            })
            .otherwise({ redirectTo: '/signup' });
    }

    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
    function run($rootScope, $location, $cookieStore, $http) {
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $window.location.href = '/login.html';
            }
        });
    }

})();