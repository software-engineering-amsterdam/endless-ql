using System;
using System.IO;
using System.Linq;
using Assignment1;
using Assignment1.Converters;
using Assignment1.Parser;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Assignment1Tests
{
    [TestClass]
    public class QLFormsTests
    {
        private static string _pathToNonParsableForms = "../../NonParsable/";
        private static string _pathToInvalidForms = "../../InvalidForms/";
        private static string _pathToValidForms = "../../ValidForms/";
        private static Form1 _form = new Form1();

        [TestMethod]
        public void TestNonParsableorms()
        {
            string[] nonParsableForms = Directory.GetFiles(_pathToNonParsableForms);

            foreach (string nonParsableForm in nonParsableForms)
            {
                // Call parser and assert that exception is thrown
                Exception expectedException = null;
                string nonParsableFormContent = File.ReadAllText(nonParsableForm);
                try
                {
                    TextToQLAST.ParseString(nonParsableFormContent);
                }
                catch (QLParseException exception)
                {
                    expectedException = exception;
                }
                Assert.IsNotNull(expectedException);
            }
        }

        [TestMethod]
        public void TestInvalidForms()
        {
            var presenter = new MainPresenter(_form);
            string[] invalidForms = Directory.GetFiles(_pathToInvalidForms);
            
            foreach (string invalidForm in invalidForms)
            {
                // Call checker and assert errors
                string invalidFormContent = File.ReadAllText(invalidForm);
                var astForm = TextToQLAST.ParseString(invalidFormContent);
                var messages = presenter.ValidateForm(astForm);
                Assert.IsTrue(messages.Errors.Any());
            }
        }

        [TestMethod]
        public void TestValidForms()
        {
            var presenter = new MainPresenter(_form);
            string[] validForms = Directory.GetFiles(_pathToValidForms);

            foreach (string validForm in validForms)
            {
                // Call checker and assert no errors
                string validFormContent = File.ReadAllText(validForm);
                var astForm = TextToQLAST.ParseString(validFormContent);
                var messages = presenter.ValidateForm(astForm);
                Assert.IsTrue(!messages.Errors.Any());
            }
        }

        [TestMethod]
        public void TestWarnings()
        {
            var presenter = new MainPresenter(_form);
            string duplicateLabelFormLocation = _pathToValidForms + "DuplicateLabel.txt";
            string formContent = File.ReadAllText(duplicateLabelFormLocation);
            var astForm = TextToQLAST.ParseString(formContent);
            var messages = presenter.ValidateForm(astForm);
            Assert.IsTrue(messages.Warnings.Any());
        }
    }
}
