package qlviz.gui;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import qlviz.QLSBaseVisitor;
import qlviz.QLSLexer;
import qlviz.QLSParser;
import qlviz.interpreter.style.StylesheetVisitor;
import qlviz.model.style.Stylesheet;

import java.io.IOException;

public class StyleModelBuilder {

	private final QLSBaseVisitor<Stylesheet> stylesheetVisitor;

	public StyleModelBuilder(QLSBaseVisitor<Stylesheet> stylesheetVisitor) {
		this.stylesheetVisitor = stylesheetVisitor;
	}

	public Stylesheet createFromMarkup(String path) {
        CharStream charStream = null;
		try {
			charStream = new FileReader(path).getStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		QLSLexer lexer = new QLSLexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLSParser parser = new QLSParser(tokens);
		return stylesheetVisitor.visitStylesheet(parser.stylesheet());
	}
}
