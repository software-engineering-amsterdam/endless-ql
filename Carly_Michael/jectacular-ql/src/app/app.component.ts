import { Component} from '@angular/core';
import {parse} from '../parser/QLParser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  input: string;

  parseInput() {
    try {
      console.log('parsing input');
      const output = parse(this.input, {});
      console.log(output);
    } catch (e) {
      console.log(e);
    }
  }
}
