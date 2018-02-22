package ast;

import java.util.Set;

public abstract class AstTree {
	
	Set<AstNode> nodes;
	
	public AstTree() {
		
	}

	public void AstNode(Set<AstNode> nodes) {
		this.nodes = nodes;
	}
}
