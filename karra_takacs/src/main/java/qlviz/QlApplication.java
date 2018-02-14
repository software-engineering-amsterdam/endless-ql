package qlviz;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import qlviz.*;
import qlviz.interpreter.ITreeToFormConverter;
import qlviz.interpreter.TreeToFormConverter;
import qlviz.model.Form;

public class QlApplication {
	public static void main(String[] args) {
		ICharStreamProvider charStreamProvider = new FileReader(args[0]);
		CharStream charStream;
		try {
			charStream = charStreamProvider.getStream();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		QLLexer lexer = new QLLexer(charStream);

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokens);

		ITreeToFormConverter modelConverter = new TreeToFormConverter();
		Form model = modelConverter.Convert(parser);
	}

}
