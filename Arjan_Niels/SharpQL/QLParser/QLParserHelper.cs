using Antlr4.Runtime;
using QLGrammar;
using QLParser.Analysis;
using QLParser.AST.Nodes;
using static QLGrammar.QLGrammarParser;

namespace QLParser
{
    public class QLParserHelper
    {
        public static FormNode Parse(string qlCode)
        {
            // Prepare the environment before we start parsing a new questionnaire.
            Prepare();

            AntlrInputStream inputStream = new AntlrInputStream(qlCode);
            QLGrammarLexer qLanguageLexer = new QLGrammarLexer(inputStream);
            CommonTokenStream commonTokenStream = new CommonTokenStream(qLanguageLexer);
            QLGrammarParser qLanguageParser = new QLGrammarParser(commonTokenStream);

            FormDeclarationContext formContext = qLanguageParser.formDeclaration();
            FormVisitor visitor = new FormVisitor();

            return visitor.VisitFormDeclaration(formContext);
        }

        private static void Prepare()
        {
            SymbolTable.Reset();
            Analyser.Reset();
        }
    }
}
