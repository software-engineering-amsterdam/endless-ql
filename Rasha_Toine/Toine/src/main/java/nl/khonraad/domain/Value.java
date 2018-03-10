package nl.khonraad.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Value {

	private Type type;
	private long units;
	private String text;

	public Type getType() {
		return type;
	}

	public long getUnits() {
		return units;
	}

	public String getText() {
		return text;
	}

	public Value(Type type, long units) {

		this.type = type;
		if (type.equals( Type.Boolean )) {
			this.units = units != 0 ? 1 : 0;
			return;
		}
		this.units = units;
	}

	public Value(Type type, String string) {
		SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy" );

		this.type = type;

		switch (type) {

			case Money: {
				units = (long) (Double.valueOf( string ) * 100);
				return;
			}

			case Integer: {
				units = Integer.parseInt( string );
				return;
			}

			case Boolean: {

				switch (string) {

					/*
					 * Boolean.parseBoolean(string) is caseINsensitive.
					 */
					case "True":
					case "False":
						units = Boolean.parseBoolean( string ) ? 1 : 0;
						return;
				}
				throw new RuntimeException( "Not a defined boolean value: " + string );
			}

			case Date: {

				Date date;
				try {
					date = formatter.parse( string );
				} catch (ParseException e) {
					throw new RuntimeException( "Not a defined date value: " + string );
				}
				units = date.getTime() + 3600000;

				return;

			}
			case String:
				text = string;
				return;

		}
		throw new RuntimeException(
				"Check your grammar: you defined a type there that is not implemented here." + type );
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + (int) (units ^ (units >>> 32));
		return result;
	}

	@Override
	public boolean equals( Object obj ) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Value other = (Value) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals( other.text ))
			return false;
		if (type != other.type)
			return false;
		if (units != other.units)
			return false;
		return true;
	}

	@Override
	public String toString() {

		if (type == Type.String) {

			return new ToStringBuilder( this, ToStringStyle.SIMPLE_STYLE ).append( "type", type ).append( "text", text )
					.toString();
		}
		return new ToStringBuilder( this, ToStringStyle.SIMPLE_STYLE ).append( "type", type ).append( "value", units )
				.toString();
	}

}