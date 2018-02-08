package org.uva.jomi.ql.ast;

import java.util.HashMap;
import java.util.Map;

public enum QLType {
	STRING ("string"),
	BOOLEAN ("boolean"),
	INTEGER ("integer"),
	DECIMAL ("decimal"),
	DATE ("decimal"),
	MONEY ("money");
	
	private final String name;
	private static final Map<String, QLType> lookup = new HashMap<>();
	
	// Populate the table
	static {
		for (QLType type : QLType.values()) {
			lookup.put(type.getName(), type);
		}
	}
	
	public static QLType getType(String name) {
		return lookup.get(name);
	}
	public String getName() {
		return name;
	}
	private QLType(String name) {
		this.name = name;
	}
}
