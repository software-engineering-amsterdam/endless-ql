using QLParser;
using QLParser.Analysis;
using QLParser.AST.Nodes;
using QLVisualizer.Factories;
using QLVisualizer.Elements.Managers;
using System.Collections.Generic;
using System.Linq;
using QLVisualizer.Elements.Managers.CollectionTypes;
using System;

namespace QLVisualizer.Controllers
{
    /// <summary>
    /// Stores used Widgets
    /// Handles Widget answer/value updates
    /// Abstracts Widget View
    /// </summary>
    public abstract class ElementManagerController
    {
        /// <summary>
        /// Collection of widgets, dictionary on widget identifyer
        /// </summary>
        public FormManager Form { get; private set; }

        private ParseController _parseController;

        public ElementManagerController(FormManager formManager)
        {
            Form = formManager;
            _parseController = new ParseController();
        }

        /// <summary>
        /// Shows view to user
        /// </summary>
        public abstract void ShowView();

        /// <summary>
        /// Handles error display
        /// </summary>
        /// <param name="errors">Errors to show</param>
        public abstract void ShowError(params string[] errors);

        /// <summary>
        /// Displays form
        /// </summary>
        /// <param name="title">Form title</param>
        /// <param name="widgets">Widgets of form</param>
        public abstract void DisplayForm();

        /// <summary>
        /// Handles QL-language input
        /// </summary>
        /// <param name="rawQL">Raw QL-language string</param>
        public virtual void HandleQL(string rawQL)
        {
            Tuple<string[], FormManager> parseResults = _parseController.ParseQL(rawQL, this);
            if (parseResults.Item1.Length > 0)
                ShowError(parseResults.Item1);
            else
                Form = parseResults.Item2;

            // Always display
            DisplayForm();
        }

        /// <summary>
        /// Exports all widget answers to xml
        /// </summary>
        /// <returns>XML string conaining answers</returns>
        public string AnswersToXml()
        {
            return Form.ToXML();
        }

        // TODO: check if class is status -sensitive
        public virtual void Reset()
        {
            Form = null;
        }
    }
}
