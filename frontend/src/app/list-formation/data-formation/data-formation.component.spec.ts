import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataFormationComponent } from './data-formation.component';

describe('DataFormationComponent', () => {
  let component: DataFormationComponent;
  let fixture: ComponentFixture<DataFormationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DataFormationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DataFormationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
