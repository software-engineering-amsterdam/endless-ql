from multimethods import multimethod
from util.values import types
from pyqls.ast.nodes import widget


class CompatibilityTypesWidget:

    def check(self, tree):
        print("Checking compatibility between types and widgets")

    @multimethod([(widget.CheckBox, types.Boolean),
                  (widget.DropDown, types.Boolean),
                  (widget.Text, types.String),
                  (widget.SpinBox, types.Integer),
                  (widget.Text, types.Integer),
                  (widget.DropDown, types.Integer),
                  (widget.SpinBox, types.Money),
                  (widget.Text, types.Decimal),
                  (widget.SpinBox, types.Decimal)])
    def compatible(self, one, another):
        return True

    @multimethod([(widget.Widget, types.Type)])
    def compatible(self, one, another):
        return False


if __name__ == "__main__":
    print(CompatibilityTypesWidget().compatible(types.Money(), types.Decimal()))
