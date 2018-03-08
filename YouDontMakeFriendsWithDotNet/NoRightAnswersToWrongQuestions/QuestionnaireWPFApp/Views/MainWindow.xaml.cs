using System.Windows;
using QuestionnaireWPFApp.ViewModels;

namespace QuestionnaireWPFApp.Views
{
    public partial class MainWindow : Window
    {
        public MainWindow(IMainViewModel viewModel)
        {
            InitializeComponent();
            DataContext = viewModel;
        }
    }
}