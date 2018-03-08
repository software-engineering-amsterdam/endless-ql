form CircularDependency {
	"Question1" q1: boolean = (q2)
	"Question2" q2: boolean = (q1)
}