using System;
using System.IO;
using System.Linq;
using Assignment1;
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
        private static readonly Form1 Form = new Form1();

        [TestMethod]
        public void TestNonParsableorms()
        {
            var nonParsableForms = Directory.GetFiles(_pathToNonParsableForms);

            foreach (var nonParsableForm in nonParsableForms)
            {
                // Call parser and assert that exception is thrown
                Exception expectedException = null;
                var nonParsableFormContent = File.ReadAllText(nonParsableForm);
                try
                {
                    QLParser.ParseString(nonParsableFormContent);
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
            var presenter = new MainPresenter(Form);
            var invalidForms = Directory.GetFiles(_pathToInvalidForms);
            
            foreach (var invalidForm in invalidForms)
            {
                // Call checker and assert errors
                var invalidFormContent = File.ReadAllText(invalidForm);
                var astForm = QLParser.ParseString(invalidFormContent);
                var messages = MainPresenter.ValidateForm(astForm);
                Assert.IsTrue(messages.Errors.Any());
            }
        }

        [TestMethod]
        public void TestValidForms()
        {
            var presenter = new MainPresenter(Form);
            var validForms = Directory.GetFiles(_pathToValidForms);

            foreach (var validForm in validForms)
            {
                // Call checker and assert no errors
                var validFormContent = File.ReadAllText(validForm);
                var astForm = QLParser.ParseString(validFormContent);
                var messages = MainPresenter.ValidateForm(astForm);
                Assert.IsTrue(!messages.Errors.Any());
            }
        }

        [TestMethod]
        public void TestWarnings()
        {
            var presenter = new MainPresenter(Form);
            var duplicateLabelFormLocation = _pathToValidForms + "DuplicateLabel.txt";
            var formContent = File.ReadAllText(duplicateLabelFormLocation);
            var astForm = QLParser.ParseString(formContent);
            var messages = MainPresenter.ValidateForm(astForm);
            Assert.IsTrue(messages.Warnings.Any());
        }
    }
}
