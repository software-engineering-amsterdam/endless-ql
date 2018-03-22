import { RadioWidgetComponent } from "../../../../rendering/components/widgets/RadioWidgetComponent";
import BooleanWidgetAttribute from "./BooleanWidgetAttribute";

// yesno-radios
export default class RadioAttribute extends BooleanWidgetAttribute {
  getValue(): string {
    return "radio";
  }

  getStringValue(): string {
    return "NOT IMPLEMENTED YET";
  }

  getRenderComponent() {
    return RadioWidgetComponent;
  }
}
