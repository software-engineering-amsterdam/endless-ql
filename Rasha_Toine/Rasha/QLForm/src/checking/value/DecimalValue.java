package checking.value;

import java.math.BigDecimal;

public class DecimalValue extends AbstractValue {
  
	private BigDecimal value;
	
	public DecimalValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public Value add(Value val) {
		return val.add(this);
	}
	
	@Override
	public Value add(DecimalValue val) {
		BigDecimal result = this.value.add(val.value);
		return new DecimalValue(result);
	}
	
	@Override
	public Value sub(Value val) {
		return val.sub(this);
	}
	
	@Override
	public Value sub(DecimalValue val) {
		BigDecimal result = this.value.subtract(val.value);
		return new DecimalValue(result);
	}
	
	@Override
	public Value mul(Value val) {
		return val.mul(this);
	}
	
	@Override
	public Value mul(DecimalValue val) {
		BigDecimal result = this.value.multiply(val.value);
		return new DecimalValue(result);
	}
	
	@Override
	public Value div(Value val) {
		return val.div(this);
	}
	
	@Override
	public Value div(DecimalValue val) {
		if (!val.value.equals(0)) {
			BigDecimal result = this.value.divide(val.value);
			return new DecimalValue(result);
		} else 
			return throwException();
	}

	
	@Override
	public BooleanValue eq(Value val) {
		return val.eq(this);
	}
	
	@Override
	public BooleanValue eq(IntegerValue val) {
		return throwException();
	}
	
	@Override
	public BooleanValue gt(Value val) {
		return val.gt(this);
	}
	
	@Override
	public BooleanValue gt(DecimalValue val) {
		int result = this.value.compareTo(val.value);
		if (result == 1)
			return new BooleanValue(true);
		else
			return new BooleanValue(false);
	}

	@Override
	public BooleanValue gEq(Value val) {
		return val.gt(this).or(val.eq(this));
	}
	
	@Override
	public BooleanValue gEq(DecimalValue val) {
		int result = this.value.compareTo(val.value);
		if (result == 2 || result == 0 )
			return new BooleanValue(true);
		else
			return new BooleanValue(false);
	}

	@Override
	public BooleanValue lt(Value val) {
		return val.lt(this);
	}
	
	@Override
	public BooleanValue lt(DecimalValue val) {
		int result = this.value.compareTo(val.value);
		if (result == 2)
			return new BooleanValue(true);
		else
			return new BooleanValue(false);
	}
	
	
	@Override
	public BooleanValue lEq(Value val) {
		return val.lt(this).or(val.eq(this));
	}
	
	@Override
	public BooleanValue lEq(DecimalValue val) {
		int result = this.value.compareTo(val.value);
		if (result == 1 || result == 0 )
			return new BooleanValue(true);
		else
			return new BooleanValue(false);
	}
	
	/*@Override
	public DecimalValue neg() {
		return new DecimalValue(this.value.multiply(new BigDecimal(-1)));
	}

	@Override
	public DecimalValue pos() {
		return new DecimalValue(this.value.multiply(new BigDecimal(+1)));
	}*/

	@Override
	public BooleanValue or(Value val) {
		return throwException();
	}

	
	@Override
	public BooleanValue and(Value val) {
		return throwException();
	}
	

	@Override
	public BooleanValue eq(BooleanValue val) {
		return BooleanValue.FALSE;
	}

	
	@Override
	public BooleanValue eq(StringValue val) {
		return BooleanValue.FALSE;
	}
	

	@Override
	public BooleanValue eq(DateValue val) {
		return BooleanValue.FALSE;
	}
	

	@Override
	public BooleanValue eq(DecimalValue val) {
		return BooleanValue.FALSE;
	}
	
	@Override
	public <T extends Value> T throwException() {
		throw new UnsupportedOperationException(getClass().getName());
	}

	@Override
	public Value translate(String str) {
		// TODO Auto-generated method stub
		return null;
	}

}