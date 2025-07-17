@GlossySprays
Feature: Retrieving the Glossy Sprays
Scenario Outline: Retreiving the Glossy Sprays
		Given user landed on Inkarto main page
		When user hover mouse on the "<artsupplies>"
		And hover the mouse on "<paints>"
		And user click on the "<spray>" from dropdown list
		Then user navigates to page of spray products
		Then retrives the list of sprays containing "<glossy>"
		Examples:
		|artsupplies|paints|spray|glossy|
		|Art Supplies|Paints|Spray|Glossy|