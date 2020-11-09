import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VentaProdcutoTemporalComponent } from './venta-prodcuto-temporal.component';

describe('VentaProdcutoTemporalComponent', () => {
  let component: VentaProdcutoTemporalComponent;
  let fixture: ComponentFixture<VentaProdcutoTemporalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VentaProdcutoTemporalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VentaProdcutoTemporalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
