// Reference to identifier in computed question out of scope
form testForm {
	"First Question"
	testIdentifier : boolean
	if (testIdentifier) {
		"Second Question"
		anotherIdentifier : integer
	}
	"Third Question"
	unknownIdentifier : integer = anotherIdentifier + 1
}