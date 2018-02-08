using Antlr4.Runtime;
using QL_Parser.Models;
using QLanguage;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Parser
{
    public class QLParserHelper
    {
        public static void Parse(String file)
        {
            AntlrInputStream inputStream = new AntlrInputStream(file);
            QLanguageLexer qLanguageLexer = new QLanguageLexer(inputStream);
            CommonTokenStream commonTokenStream = new CommonTokenStream(qLanguageLexer);
            QLanguageParser qLanguageParser = new QLanguageParser(commonTokenStream);

            QLanguageParser.FormDeclarationContext formContext = qLanguageParser.formDeclaration();
            FormVisitor visitor = new FormVisitor();


            Form form = (Form)visitor.VisitFormDeclaration(formContext);

            Console.WriteLine("Parsed a form with the name: {0}", form.Name);


            Console.ReadLine();
        }
    }
}
