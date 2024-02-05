import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAdminAssistantComponent } from './add-admin-assistant.component';

describe('AddAdminAssistantComponent', () => {
  let component: AddAdminAssistantComponent;
  let fixture: ComponentFixture<AddAdminAssistantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddAdminAssistantComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddAdminAssistantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
