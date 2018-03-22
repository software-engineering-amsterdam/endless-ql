import { DropdownWidgetComponent } from "../../../../rendering/components/widgets/DropdownWidgetComponent";
import BooleanWidgetAttribute from "./BooleanWidgetAttribute";

// yesno-dropdown (for booleans)
export default class DropdownAttribute extends BooleanWidgetAttribute {
  getRenderComponent() {
    return DropdownWidgetComponent;
  }

  getValue(): string {
    return "dropdown";
  }

  getStringValue(): string {
    return "NOT IMPLEMENTED YET";
  }

}
