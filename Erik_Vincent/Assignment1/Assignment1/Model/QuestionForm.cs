﻿using System.Collections.Generic;
using System.Runtime.CompilerServices;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;

[assembly: InternalsVisibleTo("Assignment1Tests")]

namespace Assignment1.Model
{
    public class QuestionForm
    {
        public string Id { get; }
        public List<Content> Content { get; }

        public QuestionForm(string id, List<Content> content)
        {
            Id = id;
            Content = content;
        }

        public static QuestionForm ParseString(string input)
        {
            ICharStream stream = CharStreams.fromstring(input);
            ITokenSource lexer = new QLLexer(stream);
            ITokenStream tokens = new CommonTokenStream(lexer);
            QL parser = new QL(tokens);
            QL.FormContext context = parser.form();
            QLListener listener = new QLListener();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.Walk(listener, context);
            return listener.Form;
        }
    }
}
