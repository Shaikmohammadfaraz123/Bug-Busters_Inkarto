Feature: User retrives the data from Inkarto
Background:
		Given user is on to the inkarto
@Feildlevel	
Scenario: Retrieving Followers		
		When user see Followers of website on top
		Then Retrieve the number of followers
@Feildlevel	
Scenario: Retrieving the Stationary		
		When user hover mouse on to stationary
		And user hover the mouse to subMenu stickyNotes
		Then user retrive the list of notes
@Feildlevel		
Scenario: Checking for presence of QuillingPaper 		
		When user hover mouse on to Craft Material
		And user goes to submenu hover mouse to paper
		Then user check whether QuillingPaper display or not
@Feildlevel		
Scenario: Retrieving the Data for Connect with Inkarto		
		When user scroll down to footer connect with us
		Then retrive the data of connect with us
@Feildlevel		
Scenario: Retrieving Support Urls from Inkarto		
		When user scroll down to SupportInkarto
		Then retrive the Hyperlinks of the website
@Feildlevel		
Scenario: Validating the Email for Lets get in Touch		
		When user scroll down to letsGet in touch
		And user give wrong credential
		And click on the subscribe 
		Then user will get the error message
		 
		