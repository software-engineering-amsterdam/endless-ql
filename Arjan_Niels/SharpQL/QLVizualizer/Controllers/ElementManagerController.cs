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
        protected FormManager _form;

        private ParseController _parseController;

        public ElementManagerController(FormManager formManager)
        {
            _form = formManager;
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
                _form = parseResults.Item2;

            // Always display
            DisplayForm();
        }

        /// <summary>
        /// Start notifying subscribed that the value of the widget has changed
        /// </summary>
        /// <param name="elementManagerID">ID of the changed elementManagers value</param>
        public void ValueUpdate(string elementManagerID)
        {
            _form.NotifyChange(elementManagerID);
        }

        /// <summary>
        /// Exports all widget answers to xml
        /// </summary>
        /// <returns>XML string conaining answers</returns>
        public string AnswersToXml()
        {
            return _form.ToXML();
        }

        // TODO: check if class is status -sensitive
        public virtual void Reset()
        {
            _form = null;
        }

        public ElementManager GetElementManager(string elementID)
        {
            // TODO: Get elementmanager by id
            return null;
        }
    }
}
