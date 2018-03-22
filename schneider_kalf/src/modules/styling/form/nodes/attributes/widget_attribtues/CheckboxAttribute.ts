import { CheckboxWidgetComponent } from "../../../../rendering/components/widgets/CheckboxWidgetComponent";
import BooleanWidgetAttribute from "./BooleanWidgetAttribute";

export default class CheckboxAttribute extends BooleanWidgetAttribute {
  getRenderComponent() {
    return CheckboxWidgetComponent;
  }

  getValue(): string {
    return "checkbox";
  }

  getStringValue(): string {
    return "";
  }
}
