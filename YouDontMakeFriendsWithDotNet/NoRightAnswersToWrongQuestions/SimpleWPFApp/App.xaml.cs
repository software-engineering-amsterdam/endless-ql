using System.Windows;
using Microsoft.Extensions.DependencyInjection;

namespace SimpleWPFApp
{
    public partial class App : Application
    {
        protected override void OnStartup(StartupEventArgs e)
        {
            base.OnStartup(e);

            var bootstrapper = new Bootstrapper();
            var container = bootstrapper.Bootstrap();

            var serviceProvider = container.BuildServiceProvider();
            var questionnaireViewModel = serviceProvider
                .GetService<IQuestionnaireViewModel>();
            MainWindow = new QuestionnaireView(questionnaireViewModel);
            MainWindow.Show();
            questionnaireViewModel.Load();
        }
    }
}