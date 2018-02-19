using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace QL_Visualizer.Tests.Elements
{
    public interface IElementFactoryTest
    {

        #region Create
        [TestMethod]
        void CreateIntElementTest();

        [TestMethod]
        void CreateBoolElementTest();

        [TestMethod]
        void CreateStringElementTest();

        #endregion

        #region Update
        [TestMethod]
        void UpdateIntElementTest();

        [TestMethod]
        void UpdateBoolElementTest();

        [TestMethod]
        void UpdateStringElementTest();
        #endregion
    }
}
