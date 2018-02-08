import { Component} from '@angular/core';
import {ParserService} from './services/parser.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  input: string;

  constructor (private parser: ParserService) {}

  parseInput() {
    console.log(this.parser.parseInput(this.input));
  }
}
