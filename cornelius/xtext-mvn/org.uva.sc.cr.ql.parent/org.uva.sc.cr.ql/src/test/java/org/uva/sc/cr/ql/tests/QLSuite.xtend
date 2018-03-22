package org.uva.sc.cr.ql.tests

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite)
@Suite.SuiteClasses(
	QLParsingTest,
	QLValidationTest,
	QLExpressionValidatorTest,
	QLExpressionEvaluatorTest,
	QLExpressionEvaluatorMoneyTest
)
class QLSuite {
	
}