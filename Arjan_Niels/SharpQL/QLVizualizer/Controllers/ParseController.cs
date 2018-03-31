using QLParser;
using QLParser.Analysis;
using QLParser.AST.QL;
using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Factories;
using System.Collections.Generic;

namespace QLVisualizer.Controllers
{
    public class ParseController
    {
        public FormManager Parse(string rawQl, string rawQls, ElementManagerController elementManagerController, ref List<string> errors)
        {
            FormNode formNode = QLParserHelper.Parse(rawQl);
            QLSNode qlsNode = QLSParserHelper.Parse(rawQls);
            if (!Analyser.Analyse(formNode, qlsNode))
            {
                errors.AddRange(Analyser.GetErrors());
                return null;
            }

            FormManager result = ElementManagerFactory.CreateForm(formNode, elementManagerController);
            result = ElementManagerFactory.ApplyQLS(result, qlsNode, elementManagerController, ref errors);
            return result;
        }
    }
}
