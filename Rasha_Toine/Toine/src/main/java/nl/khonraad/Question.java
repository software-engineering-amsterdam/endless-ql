package nl.khonraad;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Question {

	String variable;
	String label;
	String type;
	int value;

	public Question(String variable, String label, String type) {
		this.variable = variable;
		this.label = label;
		this.type = type;
	}


	public int getValue() {
		return value;
	}

	public String setValue(String value) {
		
		switch (type) {
		
			case "integer": {
				this.value = Integer.parseInt(value);
			}
			break;
			
			case "boolean": {
				if ("true".equals(value)) {
					this.value = 1;
				} else {
					if ("false".equals(value)) {
						this.value = 0;
					} else {
						this.value = (Integer.parseInt(value) == 0) ? 0 : 1 ;
					}
				}
			}
			break;
		
		}
		
		return value;
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