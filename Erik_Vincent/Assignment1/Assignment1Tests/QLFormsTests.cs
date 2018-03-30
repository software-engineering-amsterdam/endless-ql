using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using Assignment1.Model.QL.RenderTree;
using Assignment1.Model.QL.RenderTree.QLExpression;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Assignment1Tests
{
    [TestClass]
    public class QLFormsTests
    {
        private static string _pathToInvalidForms = "../../InvalidForms/";
        private static string _pathToValidForms = "../../ValidForms/";

        [TestMethod]
        public void TestInvalidForms()
        {
            string[] invalidForms = Directory.GetFiles(_pathToInvalidForms);
            
            foreach (string invalidForm in invalidForms)
            {
                // Call checker and assert errors
            }

            Assert.AreEqual(7, invalidForms.Length);
        }

        [TestMethod]
        public void TestValidForms()
        {
            string[] validForms = Directory.GetFiles(_pathToValidForms);

            foreach (string validForm in validForms)
            {
                // Call checker and assert no errors
            }

            Assert.AreEqual(1, validForms.Length);
        }

        [TestMethod]
        public void TestWarnings()
        {

        }
    }
}
