package nl.khonraad.domain;

import java.math.BigDecimal;

public class Value {

	private String type;
	private int value;

	public String getType() {
		return type;
	}
	
	public int getValue() {
		return value;
	}

	public Value(String type, int value) {
		
		this.type = type;
		this.value = value;
	}

	public Value(String type, String string) {

		this.type = type;

		switch (type) {

			case "money": {
				value = (int) (Double.valueOf(string) * 100);
				break;
			}

			case "integer": {
				value = Integer.parseInt(string);
				break;
			}

			case "boolean": {
				switch (string) {
					case "True":
						value = 1;
						break;
					case "False":
						value = 0;
						break;
				}
			}
		}
	}

	public Value parseAndSetValue(String s) {
		
		this.value = parseValue(s);
		return this;
	}

	private int parseValue(String s) {

		switch (type) {

			case "money": {
				BigDecimal d = new BigDecimal(s);
				return (int) (d.doubleValue() * 100);
			}

			case "integer": {
				return Integer.parseInt(s);
			}

			case "boolean": {
				switch (s) {
					case "True":
						return 1;
					case "False":
						return 0;
					default: {
						try {
							int i = Integer.parseInt(s);
							if (i != 0) {
								i = 1;
							}
							return i;
						} catch (NumberFormatException numberFormatException) {
							throw new RuntimeException("What do you mean by [" + s + "]?");
						}
					}
				}
			}
			default:
				throw new RuntimeException(
						"Check Antlr grammar. You defined an type that isn't implemented here: \"" + type + "\"");
		}
	}

}