import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaintingFormComponent } from './painting-form.component';

describe('PaintingFormComponent', () => {
  let component: PaintingFormComponent;
  let fixture: ComponentFixture<PaintingFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PaintingFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaintingFormComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
