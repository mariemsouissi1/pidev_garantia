import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardClaimsComponent } from './card-claims.component';

describe('CardClaimsComponent', () => {
  let component: CardClaimsComponent;
  let fixture: ComponentFixture<CardClaimsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardClaimsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardClaimsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
