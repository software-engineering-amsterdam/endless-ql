using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionnaireDomain.Entities.Domain
{
    public class SymbolTable<T> : ISymbolTable<T>
    {
        private readonly Dictionary<Guid, T> m_symbols = new Dictionary<Guid, T>();

        public void Add(Guid variableRef, T value)
        {
            if (!m_symbols.ContainsKey(variableRef))
            {
                return;
            }

            m_symbols[variableRef] = value;
        }

        public void Update(Guid variableRef, T value)
        {
            if (m_symbols.ContainsKey(variableRef))
            {
                m_symbols[variableRef] = value;
            }
            else
            {
                Add(variableRef, value);
            }
        }

        public T Lookup(Guid id)
        {
            T ret;
            return m_symbols.TryGetValue(id, out ret) ? ret : default(T);
        }
    }
}