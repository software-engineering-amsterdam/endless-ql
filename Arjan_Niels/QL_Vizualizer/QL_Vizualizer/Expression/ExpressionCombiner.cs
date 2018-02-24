using System.Collections.Generic;
using System.Linq;

namespace QL_Vizualizer.Expression
{
    /// <summary>
    /// Class to combine Expression values
    /// </summary>
    public static class ExpressionCombiner
    {
        /// <summary>
        /// Combines values of the same type
        /// </summary>
        /// <typeparam name="T">Type of all values</typeparam>
        /// <param name="items">Values</param>
        /// <returns>Combined value</returns>
        public static T Combine<T>(IEnumerable<T> items)
        {
            // Bool case
            if (typeof(T) == typeof(bool))
                return (T)(object)items.All(o => (bool)((object)o));

            // Int case
            if (typeof(T) == typeof(int))
                return (T)(object)items.Sum(o => (int)(object)o);

            // Double case
            if (typeof(T) == typeof(double))
                return (T)(object)items.Sum(o => (double)(object)o);

            return default(T);
        }
    }
}
