package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ql.interpreter.IntegerValue;
import org.uva.jomi.ui.SymbolTable;

class SymbolTableTest {
	
	@Test
	void testPut() {
		SymbolTable.getInstance().put("putTest", new IntegerValue(1));
		assertEquals(SymbolTable.getInstance().get("putTest").getValue(), 1);
		assertNotEquals(SymbolTable.getInstance().get("putTest").getValue(), 2);
	}
	
	@Test
	void testChange() {
		SymbolTable.getInstance().put("changeTest", new IntegerValue(1));
		assertEquals(SymbolTable.getInstance().get("changeTest").getValue(), 1);
		
		SymbolTable.getInstance().put("changeTest", new IntegerValue(2));
		assertNotEquals(SymbolTable.getInstance().get("changeTest").getValue(), 1);
		assertEquals(SymbolTable.getInstance().get("changeTest").getValue(), 2);
	}
	
	@Test
	void testContains() {
		SymbolTable.getInstance().put("containsTest", new IntegerValue(1));
		assertTrue(SymbolTable.getInstance().contains("containsTest"));
		assertFalse(SymbolTable.getInstance().contains("notExistingKey"));
	}
	
	@Test
	void testRemove() {
		SymbolTable.getInstance().put("removeTest", new IntegerValue(1));
		assertEquals(SymbolTable.getInstance().get("removeTest").getValue(), 1);
		
		SymbolTable.getInstance().remove("removeTest");
		assertEquals(SymbolTable.getInstance().get("removeTest"), null);
		assertFalse(SymbolTable.getInstance().contains("removeTest"));
	}


}
