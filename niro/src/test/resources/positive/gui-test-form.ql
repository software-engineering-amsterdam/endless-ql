form ConditionQuestions {
	firstName: "What is your first name?" string
	age: "How old are you?" integer
	if (age > 10) {
		lastName: "What is your last name?" string
		canRead: "Can you read?" boolean
	} else {
		lastName: "What is your last name of your parent?" string
	}
}