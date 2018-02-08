using QL_Parser.Models;
using System.Collections.Generic;
namespace QL_Parser
{
    public class SpeakVisitor : SpeakBaseVisitor<object>
    {
        public List<SpeakLine> Lines = new List<SpeakLine>();

        public override object VisitLine(SpeakParser.LineContext context)
        {
            NameContext name = context.name();
            OpinionContext opinion = context.opinion();

            SpeakLine line = new SpeakLine() { Person = name.GetText(), Text = opinion.GetText().Trim('"') };
            Lines.Add(line);

            return line;
        }
    }
}
