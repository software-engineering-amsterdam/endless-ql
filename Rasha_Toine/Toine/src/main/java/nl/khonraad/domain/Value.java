package nl.khonraad.domain;

import java.math.BigDecimal;

public class Value {

	private Type type;
	private int value;

	public Type getType() {
		return type;
	}

	public int getValue() {
		return value;
	}

	public Value(Type type, int value) {

		this.type = type;
		this.value = value;
	}

	public Value(Type type, String string) {

		this.type = type;

		switch (type) {

			case Money: {
				value = (int) (Double.valueOf(string) * 100);
				break;
			}

			case Integer: {
				value = Integer.parseInt(string);
				break;
			}

			case Boolean: {
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

			case Money: {
				BigDecimal d = new BigDecimal(s);
				return (int) (d.doubleValue() * 100);
			}

			case Integer: {
				return Integer.parseInt(s);
			}

			case Boolean: {
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
						"Check Antlr grammar. You defined a type there that isn't implemented here: \"" + type + "\"");
		}
	}

}