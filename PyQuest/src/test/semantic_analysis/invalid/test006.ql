// Identifiers dependent on each other (symmetric)
form testForm {
	"First Question"
	firstIdentifier : integer = secondIdentifier + 1
	"Second Question"
	secondIdentifier : integer = firstIdentifier + 1
}
