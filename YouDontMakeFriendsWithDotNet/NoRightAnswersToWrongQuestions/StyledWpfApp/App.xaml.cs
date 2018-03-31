using System.Windows;
using Microsoft.Extensions.DependencyInjection;
using StyledWpfApp.ViewModels;
using StyledWpfApp.Views;

namespace StyledWpfApp
{
    /// <summary>
    /// Interaction logic for App.xaml
    /// </summary>
    public partial class App : Application
    {
        protected override void OnStartup(StartupEventArgs e)
        {
            base.OnStartup(e);

            var bootstrapper = new Bootstrapper();
            var container = bootstrapper.Bootstrap();

            var serviceProvider = container.BuildServiceProvider();
            var questionnaireViewModel = serviceProvider
                .GetService<IStyledQuestionnaireViewModel>();
            MainWindow = new QuestionnaireView(questionnaireViewModel);
            MainWindow.Show();
            questionnaireViewModel.Load();
        }
    }
}
