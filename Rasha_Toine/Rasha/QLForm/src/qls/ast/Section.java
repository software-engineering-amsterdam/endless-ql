package qls.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import ql.ast.AstNode;
import qls.visiting.ItemVisitor;
import qls.ast.rule.*;

public class Section extends AstNode {
	
	private final String name;
	private final List<Item> items;

	//construction
	public Section(String name, List<Item> items) {
		this.name = name;
		this.items = items;
	}

	public String getName() {
		return name;
	}

	public List<Item> getItems() {
		return Collections.unmodifiableList(items);
	}

}
