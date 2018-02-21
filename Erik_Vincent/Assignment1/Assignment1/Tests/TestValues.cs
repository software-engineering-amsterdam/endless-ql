using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1.Tests
{
    class TestValues
    {
        #region SimpleForm

        public static string SimpleQId
        {
            get
            {
                return "hasChecked";
            }
        }

        public static string SimpleQLabel
        {
            get
            {
                return "\"Programming, ******, do you speak it?\"";
            }
        }

        public static string SimpleQType
        {
            get
            {
                return "boolean";
            }
        }

        public static string SingleSimpleQForm
        {
            get
            {
                return "form simpleForm { " + SimpleQId + ": " + SimpleQLabel + " " + SimpleQType + "; }";
            }
        }

        #endregion SimpleForm

        #region SimpleIfStatement

        #endregion SimpleIfStatement
    }
}
