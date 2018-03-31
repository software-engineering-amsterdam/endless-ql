using System.Windows.Controls;
using StyledWpfApp.ViewModels;

namespace StyledWpfApp.Views
{
    /// <summary>
    /// Interaction logic for PageView.xaml
    /// </summary>
    public partial class PageView : UserControl
    {
        public PageView(IPageViewModel viewModel)
        {
            InitializeComponent();
            DataContext = viewModel;
        }
    }
}
