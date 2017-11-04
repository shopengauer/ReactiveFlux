import 'dart:async';
import 'package:WebAngular/src/directives/highlight.dart';
import 'package:WebAngular/src/domain/word.dart';
import 'package:WebAngular/src/services/mock_client.dart';
import 'package:WebAngular/src/services/word_http_service.dart';
import 'package:angular/angular.dart';
import 'package:angular_components/angular_components.dart';



// AngularDart info: https://webdev.dartlang.org/angular
// Components info: https://webdev.dartlang.org/components

@Component(
  selector: 'my-app',
  styleUrls: const ['app_component.css'],
  templateUrl: 'app_component.html',
  directives: const [materialDirectives, HighlightDirective, CORE_DIRECTIVES],
  providers: const [materialProviders, WordHttpService],
)
class AppComponent implements OnInit{


 List<Word> wordList;
 String word;
 final WordHttpService _wordHttpService;

 AppComponent(this._wordHttpService);

 @override
 ngOnInit() async {
   print(_wordHttpService);
   wordList = await getWords();
  // word = await getWord();
 }

 Future<List<Word>> getWords() async {
   return await _wordHttpService.getWords('word/book/all-tokens');
 }

 Future<String> getWord() async {
   return await _wordHttpService.getWord('word/book/all-tokens');
 }




}


