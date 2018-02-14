package utils;

public class CodeReference {

	private final int startLine;
	private final int endLine;
	private final int startColumn;
	private final int endColumn;

	public CodeReference() {
		this.startLine = 0;
		this.endLine = 0;
		this.startColumn = 0;
		this.endColumn = 0;
	}

	public CodeReference(int startLine, int endLine, int startColumn, int endColumn) {
		this.startLine = startLine;
		this.endLine = endLine;
		this.startColumn = startColumn;
		this.endColumn = endColumn;
	}

	public int getStartLine() {
		return startLine;
	}
	
	public int getEndLine() {
		return endLine;
	}
	
	public int getStartColumn() {
		return startColumn;
	}
	
	public int getEndColumn() {
		return endColumn;
	}
}