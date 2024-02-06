import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewEntrepriseComponent } from './add-new-entreprise.component';

describe('AddNewEntrepriseComponent', () => {
  let component: AddNewEntrepriseComponent;
  let fixture: ComponentFixture<AddNewEntrepriseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddNewEntrepriseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddNewEntrepriseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
