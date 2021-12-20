import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModiprofileComponent } from './modiprofile.component';

describe('ModiprofileComponent', () => {
  let component: ModiprofileComponent;
  let fixture: ComponentFixture<ModiprofileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModiprofileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModiprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
