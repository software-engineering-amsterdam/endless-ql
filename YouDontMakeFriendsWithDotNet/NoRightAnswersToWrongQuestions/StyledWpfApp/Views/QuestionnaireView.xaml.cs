using System.Windows;
using StyledWpfApp.ViewModels;

namespace StyledWpfApp.Views
{
    /// <summary>
    /// Interaction logic for QuestionnaireView.xaml
    /// </summary>
    public partial class QuestionnaireView : Window
    {
        public QuestionnaireView(IStyledQuestionnaireViewModel viewModel)
        {
            InitializeComponent();
            DataContext = viewModel;
        }
    }
}
