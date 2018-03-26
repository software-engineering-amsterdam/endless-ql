import WidgetAttribute from "../WidgetAttribute";
import { SliderWidgetComponent } from "../../../../rendering/components/widgets/SliderWidgetComponent";

export default class SliderWidgetAttribute extends WidgetAttribute {
  getValue(): string {
    return "slider";
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
