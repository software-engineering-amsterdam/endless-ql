using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using QuestionnaireWPFApp.ViewModels;

namespace QuestionnaireWPFApp.Views
{
    public partial class NavigationView : UserControl, IView<NavigationViewModel>
    {
        public NavigationView()
        {
            InitializeComponent();
        }

        public NavigationViewModel ViewModel
        {
            get { return (NavigationViewModel)DataContext; }
            set { DataContext = value; }
        }
    }
}
