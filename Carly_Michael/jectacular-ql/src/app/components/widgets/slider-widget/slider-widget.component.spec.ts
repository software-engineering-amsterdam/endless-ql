import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SliderWidgetComponent } from './slider-widget.component';
import {MaterialModule} from '../../../material.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

describe('SliderWidgetComponent', () => {
  let component: SliderWidgetComponent;
  let fixture: ComponentFixture<SliderWidgetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        MaterialModule,
        FormsModule,
        ReactiveFormsModule,
      ],
      declarations: [ SliderWidgetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SliderWidgetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
