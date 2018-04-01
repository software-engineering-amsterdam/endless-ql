using System.Collections.ObjectModel;
using QuestionnaireUI.Models;
using StyledWpfApp.DataProvider;

namespace StyledWpfApp.ViewModels
{
    public class StyledQuestionnaireViewModel : Observable, IStyledQuestionnaireViewModel
    {
        private readonly IQuestionnaireDataProvider m_dataProvider;

        public StyledQuestionnaireViewModel(
            IQuestionnaireDataProvider dataProvider)
        {
            m_dataProvider = dataProvider;
        }

        public ObservableCollection<IPageViewModel> Pages { get; } 
            = new ObservableCollection<IPageViewModel>();

        public void Load()
        {
            m_dataProvider.LoadDefaultQuestionnaire();
            m_dataProvider.LoadStyleSheet();
            var questionnaire = m_dataProvider.GetSingleQuestionnaire();
            foreach (var pageWrapper in questionnaire.Pages)
            {
                Pages.Add(new PageViewModel(pageWrapper));
            }
        }
    }
}