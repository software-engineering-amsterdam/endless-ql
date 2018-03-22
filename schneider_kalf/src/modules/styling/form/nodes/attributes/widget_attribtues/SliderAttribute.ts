import WidgetAttribute from "../WidgetAttribute";
import { SliderWidgetComponent } from "../../../../rendering/components/widgets/SliderWidgetComponent";

export default class SliderAttribute extends WidgetAttribute {
  getRenderComponent() {
    return SliderWidgetComponent;
  }

  getValue(): string {
    return "slider";
  }

  getStringValue(): string {
    return "NOT IMPLEMENTED YET";
  }
}
