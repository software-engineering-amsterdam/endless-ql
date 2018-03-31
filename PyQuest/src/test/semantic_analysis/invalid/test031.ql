// Complex computed question with incompatible types
form testForm {
	"First Question"
	someIdentifier : boolean = (25.5 >= True) && ((10.0 / 3) - 15 < -10 * 2)
}