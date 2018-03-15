using System;
using System.Collections.Generic;
using QuestionaireOrchestration.Models;

namespace QuestionaireOrchestration.QueryServices.Interfaces
{
    public interface IModelQueryService<out TModel> where TModel : DomainItemModel
    {
        TModel FindByName(string name);
        TModel FindByReference(Guid reference);
        IEnumerable<TModel> GetAll();
    }
}
