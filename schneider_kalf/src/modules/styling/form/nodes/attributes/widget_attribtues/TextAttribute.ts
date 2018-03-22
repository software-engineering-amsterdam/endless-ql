import WidgetAttribute from "../WidgetAttribute";
import { TextWidgetComponent } from "../../../../rendering/components/widgets/TextWidgetComponent";

// text (for numbers and strings)
export default class TextAttribute extends WidgetAttribute {
  getRenderComponent() {
    return TextWidgetComponent;
  }

  getValue(): string {
    return "text";
  }

  getStringValue(): string {
    return "NOT IMPLEMENTED YET";
  }
}