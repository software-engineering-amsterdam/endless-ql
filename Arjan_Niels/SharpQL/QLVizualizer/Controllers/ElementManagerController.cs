using QLParser;
using QLParser.Analysis;
using QLParser.AST.Nodes;
using QLVisualizer.Factories;
using QLVisualizer.ElementManagers;
using System.Collections.Generic;
using System.Linq;
using QLVisualizer.ElementManagers.CollectionTypes;

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

        public ElementManagerController(FormManager formManager)
        {
            _form = formManager;
        }

        /// <summary>
        /// Updates the view of a widget
        /// </summary>
        /// <param name="widget">Widget to update</param>
        public abstract void UpdateView(ElementManager widget);

        /// <summary>
        /// Shows all widgets
        /// </summary>
        public abstract void ShowWidgets();

        /// <summary>
        /// Removes all widgets form view and shows them again
        /// </summary>
        public abstract void RefreshWidgets();

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
        public abstract void DisplayForm(string title, FormManager form);

        /// <summary>
        /// Handles QL-language input
        /// </summary>
        /// <param name="rawQL">Raw QL-language string</param>
        public virtual void HandleQL(string rawQL)
        {
            FormNode node = QLParserHelper.Parse(rawQL);
            if (!Analyser.Analyse(node))
            {
                ShowError(Analyser.GetErrors().ToArray());
                return;
            }

            FormManager formManager = ElementManagerFactory.CreateForm(node, this);
            DisplayForm(formManager.Text, formManager);
            //IEnumerable<ElementManager> widgets = ElementManagerFactory.CreateWidgets(node, this);
            //DisplayForm(node.FormName, widgets.ToArray());
        }

        /// <summary>
        /// Start notifying subscribed that the value of the widget has changed
        /// </summary>
        /// <param name="elementManagerID">ID of the changed elementManagers value</param>
        public void ValueUpdate(string elementManagerID)
        {
            _form.NotifyChange(elementManagerID);
        }

        public void ActiveChanged()
        {
            RefreshWidgets();
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
