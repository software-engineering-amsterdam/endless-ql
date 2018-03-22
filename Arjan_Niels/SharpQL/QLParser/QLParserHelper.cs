using Antlr4.Runtime;
using QLParser.Analysis;
using QLParser.AST.Nodes;
using QLanguage;

namespace QLParser
{
    public class QLParserHelper
    {
        public static FormNode Parse(string qlCode)
        {
            // Prepare the environment before we start parsing a new questionnaire.
            Prepare();

            AntlrInputStream inputStream = new AntlrInputStream(qlCode);
            QLanguageLexer qLanguageLexer = new QLanguageLexer(inputStream);
            CommonTokenStream commonTokenStream = new CommonTokenStream(qLanguageLexer);
            QLanguageParser qLanguageParser = new QLanguageParser(commonTokenStream);

            QLanguageParser.FormDeclarationContext formContext = qLanguageParser.formDeclaration();
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
