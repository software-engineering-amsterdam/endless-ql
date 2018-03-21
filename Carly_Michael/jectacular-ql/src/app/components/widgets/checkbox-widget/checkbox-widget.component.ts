import {Component, Input, OnChanges} from '@angular/core';
import {AbstractControl} from '@angular/forms';

@Component({
  selector: 'app-checkbox-widget',
  templateUrl: './checkbox-widget.component.html',
  styleUrls: ['./checkbox-widget.component.css']
})
export class CheckboxWidgetComponent implements OnChanges {
  @Input() label: string;
  @Input() styles;
  @Input() control: AbstractControl;

  ngOnChanges(changes) {
    console.log(changes.styles);
  }
}
