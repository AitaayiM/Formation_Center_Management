import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewFormateurComponent } from './add-new-formateur.component';

describe('AddNewFormateurComponent', () => {
  let component: AddNewFormateurComponent;
  let fixture: ComponentFixture<AddNewFormateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddNewFormateurComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddNewFormateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
