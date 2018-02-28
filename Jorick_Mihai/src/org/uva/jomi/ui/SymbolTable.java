package org.uva.jomi.ui;

import java.util.HashMap;
import java.util.Set;

public class SymbolTable {

	private static SymbolTable singleton = new SymbolTable();
	private HashMap<String, Object> symbolTable;
	
	private SymbolTable() {
		this.symbolTable = new HashMap<String, Object>();
	}
	
	public static SymbolTable getInstance() {
		return singleton;
	}
	
	
	public void put(String key, Object value) {
		System.out.println("Set " + key + " to " + value);
		this.symbolTable.put(key, value);
	}
	
	public Object get(String key) {
		if(this.contains(key)) {
			return this.symbolTable.get(key);	
		}
		return null;
	}
	
	public void remove(String key) {
		if(this.contains(key)) {
			this.symbolTable.remove(key);	
		}
	}
	
	public Boolean contains(String key) {
		return this.symbolTable.containsKey(key);
	}
	
}
