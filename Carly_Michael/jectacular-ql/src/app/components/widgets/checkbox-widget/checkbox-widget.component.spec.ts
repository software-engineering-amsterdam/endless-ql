import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckboxWidgetComponent } from './checkbox-widget.component';
import {MaterialModule} from '../../../material.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

describe('CheckboxWidgetComponent', () => {
  let component: CheckboxWidgetComponent;
  let fixture: ComponentFixture<CheckboxWidgetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        MaterialModule,
        FormsModule,
        ReactiveFormsModule,
      ],
      declarations: [ CheckboxWidgetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckboxWidgetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
