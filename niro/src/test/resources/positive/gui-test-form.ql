form ConditionQuestions {
	"What is your first name?" firstName: string
	"How old are you?" age: integer = (10)
	if (age > 10) {
		"What is your last name?" lastName: string
		"Can you read?" canRead: boolean
		if (canRead) {
		    "What is the last book you read?" bookTitle: string
		}
		"What is your monthly salary?" salary: money
	} else {
		"What is your last name of your parents?" lastName: string
	}
	"What is your date of birth?" dateOfBirth: date
}