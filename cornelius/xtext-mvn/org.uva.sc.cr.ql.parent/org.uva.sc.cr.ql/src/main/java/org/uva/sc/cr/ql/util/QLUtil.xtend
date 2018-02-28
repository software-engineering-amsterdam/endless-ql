package org.uva.sc.cr.ql.util

import java.util.HashMap
import java.util.List
import java.util.Map
import org.uva.sc.cr.ql.qL.QuestionType

class QLUtil {

	private static val Map<QuestionType, List<Operation>> ALLOWED_OPERATIONS_FOR_TYPES = #{
		QuestionType.TYPE_BOOLEAN -> #[Operation.AND, Operation.OR, Operation.NOT],
		QuestionType.TYPE_STRING -> #[Operation.PLUS, Operation.EQUALS, Operation.NOT_EQUALS],
		QuestionType.TYPE_INTEGER ->
			#[Operation.SMALLER_THAN, Operation.SMALLER_THAN_EQUALS, Operation.GREATER_THAN,
				Operation.GREATER_THAN_EQUALS, Operation.PLUS, Operation.MINUS, Operation.MULTIPLICATION,
				Operation.DIVISION],
		QuestionType.TYPE_DECIMAL ->
			#[Operation.SMALLER_THAN, Operation.SMALLER_THAN_EQUALS, Operation.GREATER_THAN,
				Operation.GREATER_THAN_EQUALS, Operation.PLUS, Operation.MINUS, Operation.MULTIPLICATION,
				Operation.DIVISION],
		QuestionType.TYPE_DATE -> #[],
		QuestionType.TYPE_MONEY ->
			#[Operation.SMALLER_THAN, Operation.SMALLER_THAN_EQUALS, Operation.GREATER_THAN,
				Operation.GREATER_THAN_EQUALS, Operation.PLUS, Operation.MINUS, Operation.MULTIPLICATION,
				Operation.DIVISION]
	}

	def private static ALLOWED_TYPES_FOR_OPERATIONS() {
		val ret = new HashMap<Operation, List<QuestionType>>
		ALLOWED_OPERATIONS_FOR_TYPES.forEach [ type, operations |
			operations.forEach [
				if (ret.containsKey(it)) {
					ret.get(it).add(type)
				} else {
					ret.put(it, newArrayList(type))
				}
			]
		]
		return ret;
	}

	def static getAllowedTypesForOperation(String op) {
		ALLOWED_TYPES_FOR_OPERATIONS.filter [ operation, allowedTypes |
			operation.literal == op
		].entrySet.head.value
	}

}
