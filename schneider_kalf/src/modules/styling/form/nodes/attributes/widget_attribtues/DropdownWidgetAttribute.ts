import { DropdownWidgetComponent } from "../../../../rendering/components/widgets/DropdownWidgetComponent";
import BooleanWidgetAttribute from "./BooleanWidgetAttribute";

// yesno-dropdown (for booleans)
export default class DropdownWidgetAttribute extends BooleanWidgetAttribute {
  getValue(): string {
    return "dropdown";
  }

  getRenderComponent() {
    return DropdownWidgetComponent;
  }

}
