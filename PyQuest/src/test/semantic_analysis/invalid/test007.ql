// Identifiers dependent on each other (transitive)
form testForm {
	"First Question"
	firstIdentifier : integer = secondIdentifier + 1
	"Second Question"
	secondIdentifier : integer = thirdIdentifier + 1
	"Third Question"
	thirdIdentifier : integer = firstIdentifier + 1
}