package ql;

import java.io.InputStream;
import java.nio.charset.Charset;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import ql.ast.AstNode;
import ql.grammar.QLLexer;
import ql.grammar.QLParser;
import ql.visitors.QLVisitorToAst;

public class QL {

	private String filePath;
	
	public QL(String filePath) {
		this.filePath = filePath;
	}
	
	public AstNode getAst() throws Exception {
		
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
		CharStream charStream;
		QLLexer lexer;
		
		if(inputStream == null) {
			charStream = CharStreams.fromFileName(filePath, Charset.forName("UTF-8"));
		} else {
			charStream = CharStreams.fromStream(inputStream, Charset.forName("UTF-8"));
		}
		
		lexer = new QLLexer(charStream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokenStream);
		
		ParseTree tree = parser.form();
		QLVisitorToAst visitor = new QLVisitorToAst();
		AstNode root = (AstNode) visitor.visit(tree);
		
		return root;
	}
}
