package org.uva.jomi.ui.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.uva.jomi.ui.interpreter.value.EmptyValue;
import org.uva.jomi.ui.interpreter.value.GenericValue;

public class SymbolTable {

	private static SymbolTable singleton = new SymbolTable();
	private HashMap<String, GenericValue> symbolTable;
	
	private List<SymbolTableListener> watchers = new ArrayList<SymbolTableListener>();
	
	private SymbolTable() {
		this.symbolTable = new HashMap<String, GenericValue>();
	}
	
	public static SymbolTable getInstance() {
		return singleton;
	}
	
	
	public void put(String key, GenericValue value) {
		this.symbolTable.put(key, value);
		
		for(SymbolTableListener element : this.watchers) {
			element.update(key, value);
		}
	}
	
	public GenericValue get(String key) {
		if(this.contains(key)) {
			return this.symbolTable.get(key);	
		}
		return new EmptyValue();
	}
	
	public void remove(String key) {
		if(this.contains(key)) {
			this.symbolTable.remove(key);	
		}
	}
	
	public Boolean contains(String key) {
		return this.symbolTable.containsKey(key);
	}
	
	
	public void addWatcher(SymbolTableListener watcher) {
		this.watchers.add(watcher);
	}
	
	public void removeWatcher(SymbolTableListener watcher) {
		this.watchers.remove(watcher);
	}
	
}
