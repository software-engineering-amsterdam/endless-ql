import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RadioWidgetComponent } from './radio-widget.component';
import {MaterialModule} from '../../../material.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

describe('RadioWidgetComponent', () => {
  let component: RadioWidgetComponent;
  let fixture: ComponentFixture<RadioWidgetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        MaterialModule,
        FormsModule,
        ReactiveFormsModule,
      ],
      declarations: [ RadioWidgetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RadioWidgetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
