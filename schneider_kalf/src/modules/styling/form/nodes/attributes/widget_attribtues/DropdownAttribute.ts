import WidgetAttribute from "../WidgetAttribute";
import { DropdownWidgetComponent } from "../../../../rendering/components/widgets/DropdownWidgetComponent";

// yesno-dropdown (for booleans)
export default class DropdownAttribute extends WidgetAttribute {
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
