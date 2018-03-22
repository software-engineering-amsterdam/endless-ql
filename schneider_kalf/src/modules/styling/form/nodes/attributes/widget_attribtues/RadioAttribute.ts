import WidgetAttribute from "../WidgetAttribute";
import { RadioWidgetComponent } from "../../../../rendering/components/widgets/RadioWidgetComponent";

// yesno-radios
export default class RadioAttribute extends WidgetAttribute {

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
