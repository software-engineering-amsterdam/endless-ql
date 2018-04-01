using System;
using System.Globalization;
using System.Windows.Data;

namespace StyledWpfApp.Views
{
    internal class BooleanToComboConverter : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {

            return value != null && (bool)value ? 0 : 1;
        }

        public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            return value != null && (int)value == 0;
        }
    }
}