package nl.khonraad.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Value {

	private Type type;
	private int units;

	public Type getType() {
		return type;
	}

	public int getUnits() {
		return units;
	}

	public Value(Type type, int units) {

		this.type = type;
		if (type.equals( Type.Boolean)) {
			this.units = units != 0 ? 1 : 0;
			return;
		}
		this.units = units;
	}

	public Value(Type type, String string) {

		this.type = type;

		switch (type) {

			case Money: {
				units = (int) (Double.valueOf(string) * 100);
				return;
			}

			case Integer: {
				units = Integer.parseInt(string);
				return;
			}

			case Boolean: {

				switch (string) {
				
					/*
					 * Boolean.parseBoolean(string) is caseINsensitive.
					 */
					case "True":
					case "False":
						units = Boolean.parseBoolean(string) ? 1 : 0;
						return;
				}
				throw new RuntimeException("Not a defined boolean value: " + string);
			}
		}
		throw new RuntimeException("Check your grammar: you defined a type there that is not implemented here." + type);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + units;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Value other = (Value) obj;
		if (type != other.type)
			return false;
		if (units != other.units)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("type", type).append("value", units)
				.toString();
	}

}