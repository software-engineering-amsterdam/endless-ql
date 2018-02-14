Feature: Bad Form Description Displays Errors
	When a syntactically or symantically incorrect Questionnaire Description is provided
	As a User
	I Want to be informed that the Description is incorrect

Scenario: Bad Questionnaire Description from a file
	Given I am using file input of the Questionnaire
	And That the input description is
	   | Text					  |
	   | Rhubarb, Rhubarb, Rubarb |
	When I start the app
	Then the result should be an appropriate error view
