Feature: Verify maps APIs

@AddPlace
Scenario Outline: Verify if place is added with AddPlace API
	Given AddPlace payload with "<name>" "<address>" "<language>"
	When User calls "AddPlace" API using "post" http method
	Then Request is successfully executed with statusCode 200
	And "status" is returned in response body as "OK"
	And "scope" is returned in response body as "APP"
	And verify if the place added with "<name>" using "GetPlace" API
	
Examples:
   |name |address             |language |
   |Gary |25,Riverside street |English  |

@DeletePlace
Scenario: Verify if place is deleted with DeletePlace API
	Given DeletePlace payload
	When User calls "DeletePlace" API using "delete" http method
	Then Request is successfully executed with statusCode 200
	And "status" is returned in response body as "OK"