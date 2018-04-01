using System.Collections.ObjectModel;
using QlsTransformer.UI.Models;

namespace StyledWpfApp.ViewModels
{
    public interface ISectionViewModel
    {
        ObservableCollection<StyledQuestionWrapper> Questions { get; }
    }
}