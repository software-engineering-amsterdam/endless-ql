using System.Windows;
using StyledWpfApp.ViewModels;

namespace StyledWpfApp.Views
{
    public partial class QuestionnaireView : Window
    {
        public QuestionnaireView(IStyledQuestionnaireViewModel viewModel)
        {
            InitializeComponent();
            DataContext = viewModel;
        }
    }
}
