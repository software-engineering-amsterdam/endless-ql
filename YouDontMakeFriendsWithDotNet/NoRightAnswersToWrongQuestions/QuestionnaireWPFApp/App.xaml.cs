using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using System.Windows;
using Microsoft.Extensions.DependencyInjection;
using QuestionnaireWPFApp.ViewModels;
using QuestionnaireWPFApp.Views;

namespace QuestionnaireWPFApp
{
    public partial class App : Application
    {
        protected override void OnStartup(StartupEventArgs e)
        {
            base.OnStartup(e);

            var bootstrapper = new Bootstrapper();
            var container = bootstrapper.Bootstrap();

            var serviceProvider = container.BuildServiceProvider();
            var mainViewModel = serviceProvider.GetService<IMainViewModel>();
            MainWindow = new MainWindow(mainViewModel);
            MainWindow.Show();
            mainViewModel.Load();
        }
    }
}