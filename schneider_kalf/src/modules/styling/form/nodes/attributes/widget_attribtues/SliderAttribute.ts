import WidgetAttribute from "../WidgetAttribute";
import { SliderWidgetComponent } from "../../../../rendering/components/widgets/SliderWidgetComponent";
import NumberValue from "../../../../../../form/values/NumberValue";

export default class SliderAttribute extends WidgetAttribute {
  getValue(): string {
    return "slider";
  }

  getStringValue(): string {
    return "";
  }

  getRenderComponent() {
    return SliderWidgetComponent;
  }

  getMinimum(): number {
    return (this.options && this.options.length === 2) ? parseInt(this.options[0], 10) : 0;
  }

  getMaximum(): number {
    return (this.options && this.options.length === 2) ? parseInt(this.options[1], 10) : 9001;
  }
}
