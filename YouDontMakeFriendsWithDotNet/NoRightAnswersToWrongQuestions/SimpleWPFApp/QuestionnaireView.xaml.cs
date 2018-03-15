using System.Windows;

namespace SimpleWPFApp
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class QuestionnaireView : Window
    {
        public QuestionnaireView(IQuestionnaireViewModel viewModel)
        {
            InitializeComponent();
            DataContext = viewModel;
        }
    }
}
