using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Assignment1
{
    public interface IMainView
    {
        event EventHandler SelectQLFile;

        void Show();
        void SetFormControl(Control control);
        void SetErrors(IEnumerable<string> errors);
        void SetWarnings(IEnumerable<string> warnings);
    }
}
