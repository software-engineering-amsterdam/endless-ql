using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QuestionnaireInfrastructure.API
{
    public interface IAppRegistration
    {
        void RegisterService<T, U>();
        void RegisterModule(IHasRegistrations module);
    }
}
