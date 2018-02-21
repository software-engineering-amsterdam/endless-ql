using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Assignment1.Model
{
    class Statement : Content
    {
        public virtual Control CreateControl() => 
            new FlowLayoutPanel() {
                AutoSize = true,
                AutoSizeMode = AutoSizeMode.GrowAndShrink,
                FlowDirection = FlowDirection.TopDown
            };
    }
}
