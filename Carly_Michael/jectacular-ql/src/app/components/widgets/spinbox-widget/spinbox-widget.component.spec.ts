import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpinboxWidgetComponent } from './spinbox-widget.component';

describe('SpinboxWidgetComponent', () => {
  let component: SpinboxWidgetComponent;
  let fixture: ComponentFixture<SpinboxWidgetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpinboxWidgetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpinboxWidgetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
