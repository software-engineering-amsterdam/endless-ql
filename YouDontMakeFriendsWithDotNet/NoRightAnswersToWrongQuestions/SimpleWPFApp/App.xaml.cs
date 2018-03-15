using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using System.Windows;
using Microsoft.Extensions.DependencyInjection;

namespace SimpleWPFApp
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
            var questionnaireViewModel = serviceProvider.GetService<IQuestionnaireViewModel>();
            MainWindow = new QuestionnaireView(questionnaireViewModel);
            MainWindow.Show();
            questionnaireViewModel.Load();
        }
    }
}
