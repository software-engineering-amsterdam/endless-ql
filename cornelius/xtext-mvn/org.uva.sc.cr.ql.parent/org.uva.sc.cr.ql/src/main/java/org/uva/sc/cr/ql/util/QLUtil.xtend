package org.uva.sc.cr.ql.util

import java.util.HashMap
import java.util.List
import java.util.Map
import org.uva.sc.cr.ql.qL.QuestionType

class QLUtil {

	public static val OPERATION_OR = "||"
	public static val OPERATION_AND = "&&"
	public static val OPERATION_EQUALS = "=="
	public static val OPERATION_NOT_EQUALS = "!="
	public static val OPERATION_SMALLER_THAN = "<"
	public static val OPERATION_SMALLER_THAN_EQUALS = "<="
	public static val OPERATION_GREATER_THAN = ">"
	public static val OPERATION_GREATER_THAN_EUQALS = ">="
	public static val OPERATION_PLUS = "+"
	public static val OPERATION_MINUS = "-"
	public static val OPERATION_MUL = "*"
	public static val OPERATION_DIV = "/"
	public static val OPERATION_NOT = "!"

	private static val Map<QuestionType, List<String>> ALLOWED_OPERATIONS_FOR_TYPES = #{
		QuestionType.TYPE_BOOLEAN -> #[OPERATION_AND, OPERATION_OR, OPERATION_NOT],
		QuestionType.TYPE_STRING -> #[OPERATION_PLUS, OPERATION_EQUALS, OPERATION_NOT_EQUALS],
		QuestionType.TYPE_INTEGER ->
			#[OPERATION_SMALLER_THAN, OPERATION_SMALLER_THAN_EQUALS, OPERATION_GREATER_THAN,
				OPERATION_GREATER_THAN_EUQALS, OPERATION_PLUS, OPERATION_MINUS, OPERATION_MUL, OPERATION_DIV],
		QuestionType.TYPE_DECIMAL ->
			#[OPERATION_SMALLER_THAN, OPERATION_SMALLER_THAN_EQUALS, OPERATION_GREATER_THAN,
				OPERATION_GREATER_THAN_EUQALS, OPERATION_PLUS, OPERATION_MINUS, OPERATION_MUL, OPERATION_DIV],
		QuestionType.TYPE_DATE -> #[],
		QuestionType.TYPE_MONEY ->
			#[OPERATION_SMALLER_THAN, OPERATION_SMALLER_THAN_EQUALS, OPERATION_GREATER_THAN,
				OPERATION_GREATER_THAN_EUQALS, OPERATION_PLUS, OPERATION_MINUS, OPERATION_MUL, OPERATION_DIV]
	}

	def static getAllowedOperationsForTypes() {
		return ALLOWED_OPERATIONS_FOR_TYPES
	}

	def static getAllowedTypesForOperations() {
		val ret = new HashMap<String, List<QuestionType>>
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

}
