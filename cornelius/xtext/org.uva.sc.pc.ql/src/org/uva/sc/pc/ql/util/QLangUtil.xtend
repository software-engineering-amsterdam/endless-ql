package org.uva.sc.pc.ql.util

import java.util.HashMap
import java.util.List
import java.util.Map
import org.uva.sc.pc.ql.qLang.QuestionType
import org.uva.sc.pc.ql.qLang.TypeBool
import org.uva.sc.pc.ql.qLang.TypeDate
import org.uva.sc.pc.ql.qLang.TypeDecimal
import org.uva.sc.pc.ql.qLang.TypeInteger
import org.uva.sc.pc.ql.qLang.TypeMoney
import org.uva.sc.pc.ql.qLang.TypeString

class QLangUtil {

	public static val TYPE_BOOLEAN = "boolean"
	public static val TYPE_STRING = "string"
	public static val TYPE_INTEGER = "integer"
	public static val TYPE_DECIMAL = "decimal"
	public static val TYPE_DATE = "date"
	public static val TYPE_MONEY = "money"

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

	private static val Map<String, List<String>> ALLOWED_OPERATIONS_FOR_TYPES = #{
		TYPE_BOOLEAN -> #[OPERATION_AND, OPERATION_OR, OPERATION_NOT],
		TYPE_STRING -> #[OPERATION_PLUS, OPERATION_EQUALS, OPERATION_NOT_EQUALS],
		TYPE_INTEGER ->
			#[OPERATION_SMALLER_THAN, OPERATION_SMALLER_THAN_EQUALS, OPERATION_GREATER_THAN,
				OPERATION_GREATER_THAN_EUQALS, OPERATION_PLUS, OPERATION_MINUS, OPERATION_MUL, OPERATION_DIV],
		TYPE_DECIMAL ->
			#[OPERATION_SMALLER_THAN, OPERATION_SMALLER_THAN_EQUALS, OPERATION_GREATER_THAN,
				OPERATION_GREATER_THAN_EUQALS, OPERATION_PLUS, OPERATION_MINUS, OPERATION_MUL, OPERATION_DIV],
		TYPE_DATE -> #[],
		TYPE_MONEY ->
			#[OPERATION_SMALLER_THAN, OPERATION_SMALLER_THAN_EQUALS, OPERATION_GREATER_THAN,
				OPERATION_GREATER_THAN_EUQALS, OPERATION_PLUS, OPERATION_MINUS, OPERATION_MUL, OPERATION_DIV]
	}

	def static getAllowedOperationsForTypes() {
		return ALLOWED_OPERATIONS_FOR_TYPES
	}

	def static getAllowedTypesForOperations() {
		val ret = new HashMap<String, List<String>>
		ALLOWED_OPERATIONS_FOR_TYPES.forEach [ operation, allowedTypes |
			allowedTypes.forEach [
				if (ret.containsKey(it)) {
					ret.get(it).add(operation)
				} else {
					ret.put(it, newArrayList(operation))
				}
			]
		]
		return ret;
	}

	def static getTypeForQuestionType(QuestionType type) {
		switch (type) {
			TypeBool: TYPE_BOOLEAN
			TypeString: TYPE_STRING
			TypeInteger: TYPE_INTEGER
			TypeDecimal: TYPE_DECIMAL
			TypeDate: TYPE_DATE
			TypeMoney: TYPE_MONEY
			default: throw new MissingCaseException
		}
	}

}
