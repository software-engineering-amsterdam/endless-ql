using System;
using System.IO;
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
            }
            catch (QLParseException exception)
            {
                _view.SetErrors(exception.Exceptions);
            }
        }
    }
}
