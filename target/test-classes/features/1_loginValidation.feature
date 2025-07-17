@LoginFeature
Feature: User logging into the Inkarto
Scenario Outline: Login to the Inkarto 
		Given the user is on the Inkarto account logo
		When user scroll upto Register button
		And click on register
		And user enters valid credentials from "<loginData>"
		And click on register button
		Then home page should be display
		But user already Exist
		And user get the error message
		Examples:
		|logindata|
		|loginData|

  

    