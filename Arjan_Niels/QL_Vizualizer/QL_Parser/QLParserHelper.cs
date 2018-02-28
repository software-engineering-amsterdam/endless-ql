using Antlr4.Runtime;
using QL_Parser.Analysis;
using QL_Parser.AST.Nodes;
using QLanguage;
using System;

namespace QL_Parser
{
    public class QLParserHelper
    {
        public static FormNode Parse(String file)
        {
            SymbolTable.Reset();
            Analyser.Reset();


            AntlrInputStream inputStream = new AntlrInputStream(file);
            QLanguageLexer qLanguageLexer = new QLanguageLexer(inputStream);
            CommonTokenStream commonTokenStream = new CommonTokenStream(qLanguageLexer);
            QLanguageParser qLanguageParser = new QLanguageParser(commonTokenStream);

            QLanguageParser.FormDeclarationContext formContext = qLanguageParser.formDeclaration();
            FormVisitor visitor = new FormVisitor();

            return visitor.VisitFormDeclaration(formContext);
        }
    }
}
