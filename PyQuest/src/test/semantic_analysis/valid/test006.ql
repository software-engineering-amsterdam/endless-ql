// Identifier reference in nested computed question
form testForm {
	"First Question"
	firstCondition : boolean
	"Second Question"
	firstInteger : integer
	if (firstCondition) {
		"Third Question"
		anotherIdentifier : integer
		"Fourth Question"
		yetAnotherIdentifier : decimal = firstInteger + 1
	}
}