package org.uva.sc.pc.ql.qLang.util

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

class TypeUtil {

	public static val TYPE_BOOLEAN = "boolean"
	public static val TYPE_STRING = "string"
	public static val TYPE_INTEGER = "integer"
	public static val TYPE_DECIMAL = "decimal"
	public static val TYPE_DATE = "date"
	public static val TYPE_MONEY = "money"

	public static val OP_OR = "||"
	public static val OP_AND = "&&"
	public static val OP_EQUALS = "=="
	public static val OP_NOT_EQUALS = "!="
	public static val OP_SMALLER_THAN = "<"
	public static val OP_SMALLER_THAN_EQUALS = "<="
	public static val OP_GREATER_THAN = ">"
	public static val OP_GREATER_THAN_EUQALS = ">="
	public static val OP_PLUS = "+"
	public static val OP_MINUS = "-"
	public static val OP_MUL = "*"
	public static val OP_DIV = "/"
	public static val OP_NOT = "!"

	private static val Map<String, List<String>> ALLOWED_OPS_FOR_TYPES = #{
		TypeUtil.TYPE_BOOLEAN -> #[TypeUtil.OP_AND, TypeUtil.OP_OR, TypeUtil.OP_NOT],
		TypeUtil.TYPE_STRING -> #[TypeUtil.OP_PLUS, TypeUtil.OP_EQUALS, TypeUtil.OP_NOT_EQUALS],
		TypeUtil.TYPE_INTEGER ->
			#[TypeUtil.OP_SMALLER_THAN, TypeUtil.OP_SMALLER_THAN_EQUALS, TypeUtil.OP_GREATER_THAN,
				TypeUtil.OP_GREATER_THAN_EUQALS, TypeUtil.OP_PLUS, TypeUtil.OP_MINUS, TypeUtil.OP_MUL, TypeUtil.OP_DIV],
		TypeUtil.TYPE_DECIMAL ->
			#[TypeUtil.OP_SMALLER_THAN, TypeUtil.OP_SMALLER_THAN_EQUALS, TypeUtil.OP_GREATER_THAN,
				TypeUtil.OP_GREATER_THAN_EUQALS, TypeUtil.OP_PLUS, TypeUtil.OP_MINUS, TypeUtil.OP_MUL, TypeUtil.OP_DIV],
		TypeUtil.TYPE_DATE -> #[],
		TypeUtil.TYPE_MONEY ->
			#[TypeUtil.OP_SMALLER_THAN, TypeUtil.OP_SMALLER_THAN_EQUALS, TypeUtil.OP_GREATER_THAN,
				TypeUtil.OP_GREATER_THAN_EUQALS, TypeUtil.OP_PLUS, TypeUtil.OP_MINUS, TypeUtil.OP_MUL, TypeUtil.OP_DIV]
	}

	def static getAllowedOpsForTypes() {
		return ALLOWED_OPS_FOR_TYPES
	}

	def static getAllowedTypesForOps() {
		val ret = new HashMap<String, List<String>>
		ALLOWED_OPS_FOR_TYPES.forEach [ k, v |
			v.forEach [ p1, p2 |
				if (ret.containsKey(p1)) {
					ret.get(p1).add(k)
				} else {
					ret.put(p1, newArrayList(k))
				}
			]
		]
		return ret;
	}

	def static getTypeForQuestionType(QuestionType type) {
		switch (type) {
			TypeBool: TypeUtil.TYPE_BOOLEAN
			TypeString: TypeUtil.TYPE_STRING
			TypeInteger: TypeUtil.TYPE_INTEGER
			TypeDecimal: TypeUtil.TYPE_DECIMAL
			TypeDate: TypeUtil.TYPE_DATE
			TypeMoney: TypeUtil.TYPE_MONEY
			default: throw new MissingCaseException
		}
	}

}
