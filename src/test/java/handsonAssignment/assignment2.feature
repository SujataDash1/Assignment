Feature: API Feature for different API calls

Background:
	* url 'https://restful-booker.herokuapp.com'
	
Scenario: 'POST' : Auth Token Creation
	Given path 'auth'
	And header Content-Type = 'application/json'
	And request { "username" : "admin", "password" : "password123" }
	When method POST
	Then status 200

Scenario: 'GET' : Retrieve All Booking Ids
	Given path '/booking'
	When method GET
	Then status 200
	* print response

Scenario: 'GET' : Retrieve All Booking details by filtering with name
	* def query = { "username" : "John", "password" : "Smith" }
	Given path '/booking'
	And params query
	When method GET
	Then status 200
	* print response

Scenario: 'GET' : Retrieve All Booking details by filtering with CheckIn/CheckOut Date
	* def query = { "checkin" : "2018-01-01", "checkout" : "2019-01-01" }
	Given path '/booking'
	And params query
	When method GET
	Then status 200
	* print response

Scenario: 'GET' : Retrieve Booking Information for particular booking ID
	Given path '/booking'
	And path bookingId = 2080
	And header Accept = 'application/json'
	When method GET
	Then status 200
	* print response

Scenario: 'POST' : Create new booking
	* def jsonquery =
	"""
	{
		"firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
	}
	"""
	Given path '/booking'
	And header Content-Type = 'application/json'
	And header Accept = 'application/json'
	And request jsonquery
	When method POST
	Then status 200
	* print response
	* print response.bookingid

Scenario: 'PUT' : Update any booking
	* def jsonquery =
	"""
	{
    "firstname" : "James",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
  }
	"""
	Given path '/booking'
	And path bookingId = 2080
	And header Content-Type = 'application/json'
	And header Accept = 'application/json'
	And request jsonquery
	When method PUT
	Then status 200
	* print response

Scenario: 'PATCH' : Update any booking with a partial payload
	* def jsonquery =
	"""
	{
    "firstname" : "Phil",
    "lastname" : "Dunphy"
  }
	"""
	Given path '/booking'
	And path bookingId = 2080
	And header Content-Type = 'application/json'
	And header Accept = 'application/json'
	And request jsonquery
	When method PATCH
	Then status 200
	* print response
	
Scenario: 'DELETE' : Delete any Booking
	Given path '/booking'
	And path bookingId = 2080
	And header Content-Type = 'application/json'
	When method DELETE
	Then status 200
	* print response
	And match response == 'OK'
	
