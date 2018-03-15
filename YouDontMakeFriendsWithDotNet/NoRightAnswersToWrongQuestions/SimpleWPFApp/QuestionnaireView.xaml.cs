using System.Windows;

namespace SimpleWPFApp
{
    public partial class QuestionnaireView : Window
    {
        public QuestionnaireView(IQuestionnaireViewModel viewModel)
        {
            InitializeComponent();
            DataContext = viewModel;
        }
    }
}
