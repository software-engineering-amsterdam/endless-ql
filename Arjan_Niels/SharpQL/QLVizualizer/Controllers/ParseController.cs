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
            if (rawQl == "")
            {
                errors.Add("Cannot parse empty QL");
                return null;
            }
            else if (rawQls == "")
            {
                return OnlyQL(rawQl, elementManagerController, ref errors);
            }
            else
            {
                return QLQLS(rawQl, rawQls, elementManagerController, ref errors);
            }

        }

        protected FormManager OnlyQL(string rawQL, ElementManagerController elementManagerController, ref List<string> errors)
        {
            FormNode formNode = QLParserHelper.Parse(rawQL);
            if (!Analyser.Analyse(formNode))
            {
                errors.AddRange(Analyser.GetErrors());
                return null;
            }

            return ElementManagerFactory.CreateForm(formNode, elementManagerController);
        }

        private FormManager QLQLS(string rawQl, string rawQls, ElementManagerController elementManagerController, ref List<string> errors)
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
