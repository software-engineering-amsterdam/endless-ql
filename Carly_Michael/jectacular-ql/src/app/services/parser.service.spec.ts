import { TestBed, inject } from '@angular/core/testing';

import { ParserService } from './parser.service';

describe('ParserService', () => {
  let service: ParserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ParserService]
    });
  });

  beforeEach(inject([ParserService], (_service: ParserService) => {
    service  = _service;
  }));

  it('should be created', inject([ParserService], (service: ParserService) => {
    expect(service).toBeTruthy();
  }));

  it('should parse a ast to a form', () => {

  });
});
