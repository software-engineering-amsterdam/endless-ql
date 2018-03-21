using Assignment1.Model.QLS.AST.Style.Widget;

namespace Assignment1.Model.QLS.AST.Style
{
    public interface IStyleVisitor
    {
        void Visit(Width style);
        void Visit(Font style);
        void Visit(FontSize style);
        void Visit(Color style);
        void Visit(Slider style);
        void Visit(SpinBox style);
        void Visit(TextBox style);
        void Visit(Radio style);
        void Visit(CheckBox style);
        void Visit(DropDown style);
    }
}
