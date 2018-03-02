package nl.khonraad.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Question {

	boolean isComputed;
	String variable;
	String label;
	String type;
	int value;

	public Question(boolean isComputed, String variable, String label, String type) {
		this.isComputed = isComputed;
		this.variable = variable;
		this.label = label;
		this.type = type;
	}

	public boolean isComputed() {
		return isComputed;
	}
	public int getValue() {
		return value;
	}

	public int setValue(String value) {
		
		switch (type) {
		
			case "money": {
				this.value = Integer.parseInt(value);
			}
			break;

			case "integer": {
				this.value = Integer.parseInt(value);
			}
			break;
			
			case "boolean": {
				switch ( value.toLowerCase() ) {
					case "true":
						this.value = 1;
						break;
					case "false":
						this.value = 0;
						break;
					default:
						try {
							this.value = Integer.parseInt(value);
						} catch ( NumberFormatException numberFormatException ) {
							throw new RuntimeException("What do you mean by ["+ value +"]?");
						}
					}
				this.value = this.value == 0  ? 0 : 1;
			}
			break;
		
		}
		return this.value;
	}


	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("variable", variable)
				.append("label", label)
				.append("type", type)
				.append("value", ("boolean".equals(type)) ? ((value != 0) ? "\"EQUIVALANCE OF TRUE\"" : "\"EQUIVALANCE OF FALSE\"" ): value)
				.toString();
	}
}