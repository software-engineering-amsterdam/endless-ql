// Identifier reference in nested condition
form testForm {
	"First Question"
	firstCondition : boolean
	"Second Question"
	secondCondition : boolean
	if (firstCondition) {
		"Third Question"
		anotherIdentifier : integer
		if (secondCondition) {
			"Fourth Question"
			yetAnotherIdentifier : decimal
		}
	}
}