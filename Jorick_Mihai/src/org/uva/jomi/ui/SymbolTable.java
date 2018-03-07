package org.uva.jomi.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.uva.jomi.ql.interpreter.GenericValue;
import org.uva.jomi.ui.elements.ComputingInterface;

public class SymbolTable {

	private static SymbolTable singleton = new SymbolTable();
	private HashMap<String, GenericValue> symbolTable;
	
	public List<ComputingInterface> watchers = new ArrayList<ComputingInterface>();
	
	private SymbolTable() {
		this.symbolTable = new HashMap<String, GenericValue>();
	}
	
	public static SymbolTable getInstance() {
		return singleton;
	}
	
	
	public void put(String key, GenericValue value) {
		this.symbolTable.put(key, value);
		
		for(ComputingInterface element : this.watchers) {
			element.update();
		}
	}
	
	public GenericValue get(String key) {
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
