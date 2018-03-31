using QLParser;
using QLParser.Analysis;
using QLParser.AST.QL;
using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Factories;
using System;
using System.Collections.Generic;

namespace QLVisualizer.Controllers
{
    public class ParseController
    {
        
        public Tuple<string[], FormManager> ParseQL(string rawQL, ElementManagerController elementManagerController)
        {
            FormNode node = QLParserHelper.Parse(rawQL);
            Analyser.Reset();
            if (!Analyser.Analyse(node))
                return new Tuple<string[], FormManager>(Analyser.GetErrors().ToArray(), null);

            FormManager formManager = ElementManagerFactory.CreateForm(node, elementManagerController);
            return new Tuple<string[], FormManager>(new string[0], formManager);
        }

        public Tuple<string[], FormManager> ParseQLS(string rawQLS, FormManager form, ElementManagerController elementManagerController, ref List<string> errors)
        {
            QLSNode qlsNode = QLSParserHelper.Parse(rawQLS);
            form = ElementManagerFactory.ApplyQLS(form, qlsNode, elementManagerController, ref errors);
            return new Tuple<string[], FormManager>(new string[0], form);
        }
    }
}
