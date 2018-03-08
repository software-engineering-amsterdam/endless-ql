using System.Windows;
using QuestionnaireWPFApp.ViewModels;

namespace QuestionnaireWPFApp.Views
{
    public partial class MainWindow : Window
    {
        private IMainViewModel m_viewModel;

        public MainWindow(IMainViewModel viewModel)
        {
            InitializeComponent();
            m_viewModel = viewModel;
            DataContext = m_viewModel;
        }
    }
}