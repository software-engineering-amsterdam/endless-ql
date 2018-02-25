form ConditionQuestions {
    "How old are you?" age: integer
	"How old is your friend" age2: integer
	"The age difference is" age3: integer = (age-age2)
}