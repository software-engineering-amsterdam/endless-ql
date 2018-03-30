using System;
using System.IO;
using System.Linq;
using System.Windows.Forms;
using Assignment1.Converters;
using Assignment1.Execution;
using Assignment1.Export;
using Assignment1.Parser;
using Assignment1.Rendering;
using Assignment1.Rendering.QLS;
using Assignment1.TypeChecking;

namespace Assignment1
{
    public class MainPresenter
    {
        private readonly IMainView _view;
        private QLExecutor _executor;

        public MainPresenter(IMainView view)
        {
            _view = view;
            view.Show();
            _view.SelectQLFile += SelectQLFile;
            _view.ExportAnswers += ExportAnswers;
        }

        private void SelectQLFile(object sender, EventArgs e)
        {
            var fileDialog = new OpenFileDialog
            {
                InitialDirectory = Directory.GetCurrentDirectory()
            };
            if (fileDialog.ShowDialog() == DialogResult.OK)
            {
                _view.ClearUI();
                ParseFile(fileDialog.FileName);
            }
        }

        private void ExportAnswers(object sender, EventArgs e)
        {
            if (_executor == null || _executor.VisibleQuestions.Any(question => _executor.GetAnswer(question.Id).IsUndefined()))
            {
                MessageBox.Show("Please answer all questions first.");
                return;
            }
            var saveDialog = new SaveFileDialog
            {
                InitialDirectory = Directory.GetCurrentDirectory(),
                Filter = "CSV files (*.csv)|*.csv"
            };
            if (saveDialog.ShowDialog() == DialogResult.OK)
            {
                FormExporter.ExportToCSV(_executor, saveDialog.FileName);
            }
        }

        private void ParseFile(string inputFile)
        {
            try
            {
                var astForm = TextToQLAST.ParseString(File.ReadAllText(inputFile));
                var messages = new MessageContainer();
                messages.Add(QLASTDuplicateChecker.CheckDuplicates(astForm));
                messages.Add(QLASTScopeChecker.CheckReferenceScopes(astForm));
                if (messages.Errors.Any())
                {
                    _view.SetErrors(messages.Errors);
                }
                else
                {
                    messages.Add(QLASTCyclicDependencyChecker.CheckForCycles(astForm));
                    QLTypeChecker typechecker = new QLTypeChecker();
                    typechecker.TypeCheckQuestionForm(astForm);
                    _executor = new QLExecutor(astForm);
                    //var renderer = new QLRenderer(_executor);
                    var renderer = new QLSRenderer(_executor, QLSParser.ParseString(File.ReadAllText(inputFile + ".qls")));
                    _view.SetFormControl(renderer.Render());
                }
                _view.SetWarnings(messages.Warnings);
            }
            catch (QLParseException exception)
            {
                _view.SetErrors(exception.Exceptions);
            }
        }
    }
}
