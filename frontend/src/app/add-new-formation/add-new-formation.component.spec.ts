import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewFormationComponent } from './add-new-formation.component';

describe('AddNewFormationComponent', () => {
  let component: AddNewFormationComponent;
  let fixture: ComponentFixture<AddNewFormationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddNewFormationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddNewFormationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
