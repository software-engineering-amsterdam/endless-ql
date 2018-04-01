using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace Assignment1
{
    public interface IQLGUI
    {
        event EventHandler SelectQLFile;
        event EventHandler ExportAnswers;

        void ClearUI();
        void Show();
        void SetFormControl(Control control);
        void SetErrors(IEnumerable<string> errors);
        void SetWarnings(IEnumerable<string> warnings);
    }
}
