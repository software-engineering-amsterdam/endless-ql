using QLVisualizer.Elements.Managers.CollectionTypes;
using System;
using System.Collections.Generic;
using System.IO;

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
        /// Collection of widgets, dictionary on widget identifier
        /// </summary>
        public FormManager Form { get; private set; }

        private ParseController _parseController;

        public ElementManagerController()
        {
            Form = null;
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

        protected void HandleInput(string rawQL, string rawQLS)
        {
            List<string> errors = new List<string>();
            try
            {
                Form = _parseController.Parse(rawQL, rawQLS, this, ref errors);
            }
            catch (Exception e)
            {
                errors.Add(string.Format("A fatal error occured:{0}\n parsing will terminate", e.Message));
            }
            finally
            {
                if (errors.Count > 0)
                    ShowError(errors.ToArray());
                else
                    DisplayForm();
            }
        }

        /// <summary>
        /// Exports all widget answers to xml
        /// </summary>
        /// <returns>XML string conaining answers</returns>
        public string AnswersToXml()
        {
            return Form.ToXML();
        }

        public virtual void Reset()
        {
            Form = null;
        }

        protected void Export()
        {
            string filename = string.Format("{0}.xml", Form.Identifier);
            try
            {
                File.WriteAllText(filename, AnswersToXml());
                ShowExportedMessage(filename);
            }
            catch
            {
                ShowError(string.Format("Unable to export the answers to {0}", filename));
            }
        }

        protected abstract void ShowExportedMessage(string filename);
    }
}
