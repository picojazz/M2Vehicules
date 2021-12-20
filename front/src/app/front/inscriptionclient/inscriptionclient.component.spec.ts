import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InscriptionclientComponent } from './inscriptionclient.component';

describe('InscriptionclientComponent', () => {
  let component: InscriptionclientComponent;
  let fixture: ComponentFixture<InscriptionclientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InscriptionclientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InscriptionclientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
