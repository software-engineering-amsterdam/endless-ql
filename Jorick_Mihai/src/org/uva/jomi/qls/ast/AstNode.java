package org.uva.jomi.qls.ast;

public abstract class AstNode {
	private static int ctr = 0;

	private int id = ctr++;

	public static void resetCtr() {
		AstNode.ctr = 0;
	}

	public int getId() {
		return id;
	}
}