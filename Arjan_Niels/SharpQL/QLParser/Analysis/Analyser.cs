using QLParser.Analysis.QL.Semantic;
using QLParser.Analysis.QL.Syntactic;
using QLParser.AST.QL;
using System.Collections.Generic;
using System.Linq;

namespace QLParser.Analysis
{
    public class Analyser
    {
        #region Singleton
        private static Analyser _instance;
        public static Analyser Instance
        {
            get
            {
                if (_instance == null)
                    _instance = new Analyser();

                return _instance;
            }
        }
        #endregion

        private List<IQLAnalyser> _analysers;
        private List<ParseMessage> _messages;

        private Analyser()
        {
            this._messages = new List<ParseMessage>();
            this._analysers = new List<IQLAnalyser>()
            {
                // Syntactic
                new QuestionHasNoChildrenValidator(),
                new SingleFormValidator(),

                // Semantic
                new DuplicateVariableAnalyser(),
                new OnlyInitialisedVarsAnalyser(),
                new StatementTypeAnalyser()
            };
        }

        public static bool Analyse(QLNode node)
        {
            if (node == null)
                return false;

            var result = true;
            foreach (IQLAnalyser analyser in Instance._analysers)
                if (!analyser.Analyse(node) && result)
                    result = false;
            return result;
        }

        public static void AddMessage(string message, MessageType type)
        {
            Instance._messages.Add(new ParseMessage(message, type));
        }

        public static List<string> GetErrors()
        {
            return Instance._messages
                .Select(x => x.ToString())
                .ToList();
        }

        public static void Reset()
        {
            Instance._messages.Clear();
        }
    }
}