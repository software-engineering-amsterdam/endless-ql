import WidgetAttribute from "../WidgetAttribute";
import { SpinboxWidgetComponent } from "../../../../rendering/components/widgets/SpinboxWidgetComponent";

// spinbox (for numbers)
export default class SpinboxAttribute extends WidgetAttribute {
  getRenderComponent() {
    return SpinboxWidgetComponent;
  }

  getValue(): string {
    return "spinbox";
  }

  getStringValue(): string {
    return "NOT IMPLEMENTED YET";
  }
}
