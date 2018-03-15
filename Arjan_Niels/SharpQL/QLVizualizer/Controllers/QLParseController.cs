using QLParser;
using QLParser.Analysis;
using QLParser.AST.Nodes;
using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Factories;
using System;

namespace QLVisualizer.Controllers
{
    public class ParseController
    {
        
        public Tuple<string[], FormManager> ParseQL(string rawQL, ElementManagerController elementManagerController)
        {
            FormNode node = QLParserHelper.Parse(rawQL);
            if (!Analyser.Analyse(node))
                return new Tuple<string[], FormManager>(Analyser.GetErrors().ToArray(), null);

            FormManager formManager = ElementManagerFactory.CreateForm(node, elementManagerController);
            return new Tuple<string[], FormManager>(new string[0], formManager);
        }

        public Tuple<string[], FormManager> ParseQLS(string rawQLS, FormManager form)
        {
            QLSNode qlsNode = QLSParserHelper.Parse(rawQLS);
            //
            return new Tuple<string[], FormManager>(new string[0], form);
        }
    }
}
