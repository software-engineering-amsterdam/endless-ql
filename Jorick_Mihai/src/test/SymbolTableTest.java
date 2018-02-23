package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.uva.jomi.ui.SymbolTable;

class SymbolTableTest {
	
	@Test
	void testPut() {
		SymbolTable.getInstance().put("putTest", 1);
		assertEquals(SymbolTable.getInstance().get("putTest"), 1);
		assertNotEquals(SymbolTable.getInstance().get("putTest"), 2);
	}
	
	@Test
	void testChange() {
		SymbolTable.getInstance().put("changeTest", 1);
		assertEquals(SymbolTable.getInstance().get("changeTest"), 1);
		
		SymbolTable.getInstance().put("changeTest", 2);
		assertNotEquals(SymbolTable.getInstance().get("changeTest"), 1);
		assertEquals(SymbolTable.getInstance().get("changeTest"), 2);
	}
	
	@Test
	void testContains() {
		SymbolTable.getInstance().put("containsTest", 1);
		assertTrue(SymbolTable.getInstance().contains("containsTest"));
		assertFalse(SymbolTable.getInstance().contains("notExistingKey"));
	}
	
	@Test
	void testRemove() {
		SymbolTable.getInstance().put("removeTest", 1);
		assertEquals(SymbolTable.getInstance().get("removeTest"), 1);
		
		SymbolTable.getInstance().remove("removeTest");
		assertEquals(SymbolTable.getInstance().get("removeTest"), null);
		assertFalse(SymbolTable.getInstance().contains("removeTest"));
	}


}
