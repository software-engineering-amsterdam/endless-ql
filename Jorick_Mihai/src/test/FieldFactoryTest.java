package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ui.views.fields.BooleanField;
import org.uva.jomi.ui.views.fields.FieldFactory;
import org.uva.jomi.ui.views.fields.IntegerField;
import org.uva.jomi.ui.views.fields.TextField;

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
	void testIntegerType() {
		FieldFactory factory = new FieldFactory();
		assertTrue(factory.getField("integer") instanceof IntegerField);
	}
	
	@Test
	void testUnknownType() {
		FieldFactory factory = new FieldFactory();
		assertNull(factory.getField("unkown"));
	}


}
