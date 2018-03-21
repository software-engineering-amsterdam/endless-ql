import {Component, Input, OnChanges} from '@angular/core';
import {AbstractControl} from '@angular/forms';

@Component({
  selector: 'app-radio-widget',
  templateUrl: './radio-widget.component.html',
  styleUrls: ['./radio-widget.component.css']
})
export class RadioWidgetComponent {
  @Input() questionLabel: string;
  @Input() optionLabels: string[];
  @Input() control: AbstractControl;
  @Input() styles;
}
