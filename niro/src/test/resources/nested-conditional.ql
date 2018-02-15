form QonditionQuestions {
	firstName: "What is your first name?" string
	if (true) {
		lastName: "What is your last name?" string
	} else {
		middleName: "What is your middle name?" string
	}
}