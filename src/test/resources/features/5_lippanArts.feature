@DataExport
Feature: Retrieving the products of LippanArt
Scenario Outline: Retrieving LippanArt Products
		Given the user open the Inkarto Website
		When user click on search icon 
		And user  enter the "<lippanart>" in search text box
		Then the user see the products of "<lippanart>"
		And user retrieves the list of products based on conditions above
		Examples:
		|lippanart|
		|Lippan Art|