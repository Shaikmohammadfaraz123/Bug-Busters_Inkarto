@Regression
Feature: User get items in wishList
Scenario: Successfully get wishlist
		Given user on to the Inkarto website
		When user scroll to specialDeals
		And user click on the logo of wishlist
		And user click on wishlist icon on top most right
		Then page is redirected to wishlist
		And user retrives the list added to Wishlist
