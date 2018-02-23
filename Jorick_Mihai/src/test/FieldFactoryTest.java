package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.uva.jomi.ui.elements.fields.BooleanField;
import org.uva.jomi.ui.elements.fields.FieldFactory;
import org.uva.jomi.ui.elements.fields.InputField;
import org.uva.jomi.ui.elements.fields.MoneyField;
import org.uva.jomi.ui.elements.fields.TextField;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

class FieldFactoryTest {
	
	@Test
	void testStringType() {
		FieldFactory factory = new FieldFactory();
		assertTrue(factory.getField("string") instanceof TextField);
	}
	
	@Test
	void testBooleanType() {
		FieldFactory factory = new FieldFactory();
		assertTrue(factory.getField("boolean") instanceof BooleanField);
	}
	
	@Test
	void testMoneyType() {
		FieldFactory factory = new FieldFactory();
		assertTrue(factory.getField("money") instanceof MoneyField);
	}
	
	@Test
	void testUnknownType() {
		FieldFactory factory = new FieldFactory();
		assertNull(factory.getField("unkown"));
	}

}
