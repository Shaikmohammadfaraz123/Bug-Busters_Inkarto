@Sanity
Feature: Retrieving the BinderClips from Inkarto
Scenario Outline: Retrieving BinderClips
		Given user is on to the Inkarto Page
		When user click on search Icon
		And user search for "<files>"
		Then user redirect to page of products under "<files>"
		When user scroll to select checkbox "<binderclips>"
		Then user retrieves the list of BinderClips
		Examples:
		|files|binderclips|
		|Files|BinderClips|
		