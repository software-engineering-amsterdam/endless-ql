package qls;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import qls.ast.ASTBuilder;
import qls.ast.model.Stylesheet;
import qls.ast.visitors.TestASTTraverse;
import qls.grammar.QLSLexer;
import qls.grammar.QLSParser;

class Main {
    public static void main(String[] args) {
        String stylesheetString = "stylesheet taxOfficeExample\n" +
                "{\n" +
                "  page Housing\n" +
                "  {\n" +
                "    section \"Buying\"\n" +
                "    {\n" +
                "      question hasBoughtHouse  \n" +
                "        widget checkbox \n" +
                "    }\n" +
                "    section \"Loaning\"  \n" +
                "      question hasMaintLoan\n" +
                "  }\n" +
                "\n" +
                "  page Selling\n" +
                "  { \n" +
                "    section \"Selling\"\n" +
                "    {\n" +
                "      question hasSoldHouse\n" +
                "        widget radio(\"Yes\", \"No\") \n" +
                "      section \"You sold a house\"\n" +
                "      {\n" +
                "        question sellingPrice\n" +
                "          widget spinbox\n" +
                "        question privateDebt\n" +
                "          widget spinbox \n" +
                "        question valueResidue\n" +
                "        default money\n" +
                "        {\n" +
                "          width: 400\n" +
                "          font: \"Arial\" \n" +
                "          fontsize: 14\n" +
                "          color: #999999\n" +
                "          widget spinbox\n" +
                "        }        \n" +
                "      }\n" +
                "    }\n" +
                "    default boolean widget radio(\"Yes\", \"No\")\n" +
                "  }  \n" +
                "}\n";


        CharStream charStream = CharStreams.fromString(stylesheetString);
        QLSLexer qlsLexer = new QLSLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlsLexer);
        QLSParser qlsParser = new QLSParser(commonTokenStream);
        QLSParser.StylesheetContext stylesheetContext = qlsParser.stylesheet();
        ASTBuilder astBuilder = new ASTBuilder();

        Stylesheet stylesheet = astBuilder.visitStylesheet(stylesheetContext);

        TestASTTraverse testVisitor = new TestASTTraverse();

        stylesheet.accept(testVisitor);

        System.out.println("done");

    }
}
