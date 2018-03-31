using System;
using System.Drawing;

namespace QLVisualizer
{
    public class Hexadecimal
    {
        private byte _value;

        public Hexadecimal(int value)
        {
            _value = (byte)value;
        }

        public Hexadecimal(string value)
        {
            if (IsValid(value))
                _value = StringToByte(value);
            else
                throw new InvalidCastException("Cannot parse hexadecimal value from: " + _value);
        }

        private bool IsValid(string value)
        {
            return value.StartsWith("#");
        }

        private byte StringToByte(string value)
        {
            return Convert.ToByte(value.Substring(1), 16);
        }

        public override string ToString()
        {
            return string.Format("{0:x}", _value);
        }

        public int ToInteger()
        {
            return _value;
        }

        public Color ToColor()
        {
            ColorConverter colorConverter = new ColorConverter();

            try
            {
                return (Color)colorConverter.ConvertFromString(ToString());
            }
            catch
            {
                throw new InvalidCastException(string.Format("Cannot convert hexadecimal value: {0} to a color", ToString()));
            }
        }

        public static Hexadecimal operator +(Hexadecimal left, Hexadecimal right)
        {
            return new Hexadecimal(left._value + right._value);
        }

        public static Hexadecimal operator -(Hexadecimal left, Hexadecimal right)
        {
            return new Hexadecimal(left._value - right._value);
        }

        public static Hexadecimal operator *(Hexadecimal left, Hexadecimal right)
        {
            return new Hexadecimal(left._value * right._value);
        }

        public static Hexadecimal operator /(Hexadecimal left, Hexadecimal right)
        {
            return new Hexadecimal(left._value / right._value);
        }

        public static bool operator <(Hexadecimal left, Hexadecimal right)
        {
            return left._value < right._value;
        }

        public static bool operator >(Hexadecimal left, Hexadecimal right)
        {
            return left._value > right._value;
        }
    }
}
