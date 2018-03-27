using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1.Model.QL.AST.Value
{
    public interface IOperations
    {
        IValue Add(IValue right);
        IValue Subtract(IValue right);
        IValue Multiply(IValue right);
        IValue Divide(IValue right);
        QLBoolean GreaterThan(IValue right);
        QLBoolean LessThan(IValue right);
        QLBoolean GreaterThanOrEqual(IValue right);
        QLBoolean LessThanOrEqual(IValue right);
        QLBoolean Equal(IValue right);
        QLBoolean NotEqual(IValue right);
        QLBoolean And(IValue right);
        QLBoolean Or(IValue right);
        QLBoolean Not();
        string OperationErrorMessage(string operation, string leftType, string rightType); 
    }
}
