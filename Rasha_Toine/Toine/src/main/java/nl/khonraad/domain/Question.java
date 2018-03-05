package nl.khonraad.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Question {

	public enum QuestionType {
		COMPUTED,
		NOT_COMPUTED
	};
	
	QuestionType isComputed;
	String identifier;
	String label;
	String iotype;
	int value;

	public Question(QuestionType isComputed, String identifier, String label, String iotype) {
		this.isComputed = isComputed;
		this.identifier = identifier;
		this.label = label;
		this.iotype = iotype;
	}

	public QuestionType isComputed() {
		return isComputed;
	}
	public int getValue() {
		return value;
	}

	public int setValue(String value) {
		
		switch (iotype) {
		
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
				.append("identifier", identifier)
				.append("label", label)
				.append("type", iotype)
				.append("value", ("boolean".equals(iotype)) ? ((value != 0) ? "\"EQUIVALANCE OF TRUE\"" : "\"EQUIVALANCE OF FALSE\"" ): value)
				.toString();
	}
}