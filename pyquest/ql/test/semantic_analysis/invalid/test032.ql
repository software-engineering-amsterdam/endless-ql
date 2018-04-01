// Complex computed question with incompatible result type
form testForm {
	"First Question"
	someIdentifier : integer = (25.5 >= 10) && ((10.0 / 3) - 15 < -10 * 2)
}
