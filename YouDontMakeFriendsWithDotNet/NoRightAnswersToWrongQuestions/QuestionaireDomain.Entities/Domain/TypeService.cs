using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionnaireDomain.Entities.Domain
{
    internal class TypeService : ITypeService
    {
        public bool IsValidOperationForType(IRelationalLogicNode operation, Type type)
        {
            if (type == typeof(string) || type == typeof(bool))
            {
                return operation is IEqualityNode || operation is IInequalityNode;
            }

            return type == typeof(DateTime) || type == typeof(decimal) || type == typeof(int);
        }

        public string GetTypeDisplay(Type type)
        {
            if (type == typeof(bool))
            {
                return @"boolean";
            }
            if (type == typeof(int))
            {
                return @"integer";
            }
            if (type == typeof(string))
            {
                return @"string";
            }
            if (type == typeof(decimal))
            {
                return @"decimal";
            }

            if (type == typeof(DateTime))
            {
                return @"date";
            }

            throw new ArgumentException($@"unhandled type {type}");
        }
    }
}