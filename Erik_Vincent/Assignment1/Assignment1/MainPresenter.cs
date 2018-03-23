using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Windows.Forms;
using Assignment1.Converters;
using Assignment1.Model;
using Assignment1.Model.QL.AST;
using Assignment1.Parser;
using Assignment1.Rendering;
using Assignment1.TypeChecking;

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
                var astForm = TextToQLAST.ParseString(File.ReadAllText(inputFile));
                QLASTDuplicateChecker.CheckDuplicates(astForm);
                QLASTScopeChecker.CheckReferenceScopes(astForm);
                QLASTCyclicDependencyChecker.CheckForCycles(astForm);
                QLTypeChecker typechecker = new QLTypeChecker();
                typechecker.TypeCheckQuestionForm(astForm);
                var renderForm = QLASTToRenderTree.Convert(astForm);
                IQuestionFormRenderer renderer = new QuestionFormRenderer(renderForm);
                _view.SetFormControl(renderer.Render());
                _view.SetWarnings(typechecker.Warnings);
            }
            catch (QLParseException exception)
            {
                _view.SetErrors(exception.Exceptions);
            }
        }
    }
}
