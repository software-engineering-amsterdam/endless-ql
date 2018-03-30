import { CheckboxWidgetComponent } from "../../../../rendering/components/widgets/CheckboxWidgetComponent";
import BooleanWidgetAttribute from "./BooleanWidgetAttribute";

// TODO should not accept options
export default class CheckboxWidgetAttribute extends BooleanWidgetAttribute {
  validate() {
    return;
  }
  getRenderComponent() {
    return CheckboxWidgetComponent;
  }

  getValue(): string {
    return "checkbox";
  }
}
