import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ARegistrationComponent } from './a-registration.component';

describe('ARegistrationComponent', () => {
  let component: ARegistrationComponent;
  let fixture: ComponentFixture<ARegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ARegistrationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ARegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
