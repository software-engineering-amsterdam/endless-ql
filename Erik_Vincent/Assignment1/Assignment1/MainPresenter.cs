using System;
using System.IO;
using System.Linq;
using System.Windows.Forms;
using Assignment1.Execution;
using Assignment1.Export;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QLS.AST;
using Assignment1.Parser;
using Assignment1.Properties;
using Assignment1.Rendering;
using Assignment1.Rendering.QLS;
using Assignment1.TypeChecking;
using Assignment1.TypeChecking.QLS;

namespace Assignment1
{
    public class MainPresenter
    {
        private readonly IQLGUI _view;
        private QLExecutor _executor;

        public MainPresenter(IQLGUI view)
        {
            _view = view;
            _view.SelectQLFile += SelectQLFile;
            _view.ExportAnswers += ExportAnswers;
        }

        private void SelectQLFile(object sender, EventArgs e)
        {
            var fileDialog = new OpenFileDialog
            {
                InitialDirectory = Directory.GetCurrentDirectory()
            };
            if (fileDialog.ShowDialog() != DialogResult.OK) return;
            _view.ClearUI();
            ParseFile(fileDialog.FileName);
        }

        private void ExportAnswers(object sender, EventArgs e)
        {
            if (_executor == null || _executor.VisibleQuestions.Any(question => _executor.GetAnswer(question.Id).IsUndefined()))
            {
                MessageBox.Show(Resources.MainPresenter_ExportAnswers_Please_answer_all_questions_first_);
                return;
            }
            var saveDialog = new SaveFileDialog
            {
                InitialDirectory = Directory.GetCurrentDirectory(),
                Filter = Resources.MainPresenter_ExportAnswers_CSV_files____csv____csv
            };
            if (saveDialog.ShowDialog() == DialogResult.OK)
            {
                FormExporter.ExportToCSV(_executor, saveDialog.FileName);
            }
        }

        private void ParseFile(string inputFile)
        {
            var fileContent = File.ReadAllText(inputFile);
            try
            {
                var astForm = QLParser.ParseString(fileContent);
                var messages = ValidateForm(astForm);
                if (AnyErrors(messages))
                {
                    _view.SetErrors(messages.Errors);
                    return;
                }
                _executor = new QLExecutor(astForm);

                var qlsFileLocation = inputFile + ".qls";
                IQuestionFormRenderer renderer = new QLRenderer(_executor);
                if (File.Exists(qlsFileLocation))
                {
                    var styleSheet = QLSParser.ParseString(File.ReadAllText(qlsFileLocation));
                    messages.Add(ValidateStyleSheet(styleSheet, astForm));
                    if (AnyErrors(messages))
                    {
                        _view.SetErrors(messages.Errors);
                        return;
                    }
                    renderer = new QLSRenderer(_executor, styleSheet);
                }
                _view.SetFormControl(renderer.Render());
                _view.SetWarnings(messages.Warnings);
            }
            catch (QLParseException exception)
            {
                _view.SetErrors(exception.Exceptions);
            }
        }

        public static MessageContainer ValidateForm(QuestionForm astForm)
        {
            var messages = new MessageContainer();
            messages.Add(QLDuplicateChecker.CheckDuplicates(astForm));
            if (AnyErrors(messages)) return messages;
            messages.Add(QLScopeChecker.CheckReferenceScopes(astForm));
            if (AnyErrors(messages)) return messages;
            messages.Add(QLCyclicDependencyChecker.CheckForCycles(astForm));
            if (AnyErrors(messages)) return messages;
            messages.Add(QLTypeChecker.CheckTypes(astForm));
            return messages;
        }

        private static MessageContainer ValidateStyleSheet(StyleSheet styleSheet, QuestionForm astForm)
        {
            var messages = new MessageContainer();
            messages.Add(QLSReferenceChecker.CheckQuestionReferences(styleSheet, astForm));
            if (AnyErrors(messages)) return messages;
            messages.Add(QLSTypeChecker.CheckTypes(styleSheet, astForm));
            return messages;
        }

        private static bool AnyErrors(MessageContainer messages) => messages.Errors.Any();
    }
}
