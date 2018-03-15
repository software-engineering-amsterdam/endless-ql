using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Assignment1.Model.QL;
using Assignment1.Model.QLS;

namespace Assignment1.TypeChecking
{
    public class QLSTypeChecker : IContentVisitor
    {
        public void CheckStylesheet(Stylesheet stylesheet, QuestionForm questionForm)
        {
            stylesheet.Pages.ForEach(page => page.Sections.ForEach(Visit));
        }

        public void Visit(Section section)
        {
            section.Contents.ForEach(content => content.Accept(this));
        }

        public void Visit(QuestionStyle question)
        {
            Console.WriteLine(question.Id);
        }
    }
}
