// Reference to identifier in condition out of scope
form testForm {
	"First Question"
	testIdentifier : boolean
	if (testIdentifier) {
		"Second Question"
		anotherIdentifier : integer
	}
	if (anotherIdentifier) {
		"Unreachable Question"
		unreachableIdentifier : integer
	}
}