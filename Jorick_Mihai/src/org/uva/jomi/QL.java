package org.uva.jomi;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.jomi.ql.ast.AstBuilder;
import org.uva.jomi.ql.ast.analysis.CyclicDependencyChecker;
import org.uva.jomi.ql.ast.analysis.DuplicatedLabelChecker;
import org.uva.jomi.ql.ast.analysis.IdentifierMapBuilder;
import org.uva.jomi.ql.ast.analysis.IdentifierResolver;
import org.uva.jomi.ql.ast.analysis.TypeResolver;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.parser.antlr.*;
import org.uva.jomi.ql.parser.antlr.QLParser.ParseContext;
import org.uva.jomi.qls.parser.antlr.QLSLexer;
import org.uva.jomi.qls.parser.antlr.QLSParser;
import org.uva.jomi.ui.Questionair;
import org.uva.jomi.ui.elements.ElementBuilder;
import org.uva.jomi.ui.elements.core.Frame;
import org.uva.jomi.ui.elements.core.Panel;

public class QL {

	public static void main(String[] args) {	
		Questionair questionair = new Questionair();
		questionair.setVisibility(true);
	}

}
