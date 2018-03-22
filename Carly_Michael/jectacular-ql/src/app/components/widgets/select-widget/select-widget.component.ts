import {Component, Input} from '@angular/core';
import {AbstractControl} from '@angular/forms';

@Component({
  selector: 'app-select-widget',
  templateUrl: './select-widget.component.html',
  styleUrls: ['./select-widget.component.css']
})
export class SelectWidgetComponent {
  @Input() questionLabel: string;
  @Input() optionLabels: string[];
  @Input() control: AbstractControl;
  @Input() styles;
}
