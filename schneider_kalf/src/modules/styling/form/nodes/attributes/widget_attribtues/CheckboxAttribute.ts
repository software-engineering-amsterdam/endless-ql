import WidgetAttribute from "../WidgetAttribute";
import { CheckboxWidgetComponent } from "../../../../rendering/components/widgets/CheckboxWidgetComponent";

export default class CheckboxAttribute extends WidgetAttribute {
  getRenderComponent() {
    return CheckboxWidgetComponent;
  }

  getValue(): string {
    return "checkbox";
  }

  getStringValue(): string {
    return "NOT IMPLEMENTED YET";
  }
}
