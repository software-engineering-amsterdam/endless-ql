using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Windows.Forms;
using Assignment1.Model;
using Assignment1.Parser;
using Assignment1.Rendering;

namespace Assignment1
{
    public class MainPresenter
    {
        private readonly IMainView _view;

        public MainPresenter(IMainView view)
        {
            _view = view;
            view.Show();
            _view.SelectQLFile += SelectQLFile;
        }

        private void SelectQLFile(object sender, EventArgs e)
        {
            var fileDialog = new OpenFileDialog
            {
                InitialDirectory = Directory.GetCurrentDirectory()
            };
            if (fileDialog.ShowDialog() == DialogResult.OK)
            {
                ParseFile(fileDialog.FileName);
            }
        }

        private void ParseFile(string inputFile)
        {
            try
            {
                var form = QLListener.ParseString(File.ReadAllText(inputFile));
                IQuestionFormRenderer renderer = new QuestionFormRenderer(form);
                _view.SetFormControl(renderer.Render());
                _view.SetWarnings(form.Warnings.Values.ToList()); // TODO: find a way to show it above in form or somewhere else?
            }
            catch (QLParseException exception)
            {
                _view.SetErrors(exception.Exceptions);
            }
        }
    }
}
