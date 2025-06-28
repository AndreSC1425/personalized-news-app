import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NewsCardComponent } from './news-card.component';
import { Article } from '../../services/news.service';
import { By } from '@angular/platform-browser';

describe('NewsCardComponent', () => {
  let component: NewsCardComponent;
  let fixture: ComponentFixture<NewsCardComponent>;

  const testArticle: Article = {
    title: 'Test Title',
    description: 'Test description',
    url: 'http://test.com'
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [NewsCardComponent]
    });
    fixture = TestBed.createComponent(NewsCardComponent);
    component = fixture.componentInstance;
    component.article = testArticle;
    fixture.detectChanges();
  });

  it('should show article title and description', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('.card-title')?.textContent).toContain('Test Title');
    expect(compiled.querySelector('.card-text')?.textContent).toContain('Test description');
  });
  it('should add bookmark when button clicked', () => {
  spyOn(window.localStorage, 'getItem').and.returnValue('[]');
  const setItemSpy = spyOn(window.localStorage, 'setItem');

  component.bookmarkArticle();

  expect(setItemSpy).toHaveBeenCalled();
  });
    it('should emit remove event when remove button clicked', () => {
    component.showRemoveButton = true;
    fixture.detectChanges();

    spyOn(component.remove, 'emit');
    component.handleRemove();

    expect(component.remove.emit).toHaveBeenCalledWith(testArticle);
  });
});
// This test suite verifies that the NewsCardComponent correctly displays article details,
// allows bookmarking articles, and emits an event when the remove button is clicked.

