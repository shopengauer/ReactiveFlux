import 'dart:async';
import 'package:WebAngular/src/component/editablefield/editable_field.dart';
import 'package:WebAngular/src/component/main_layout/main_layout.dart';
import 'package:WebAngular/src/component/table/book_table.dart';
import 'package:WebAngular/src/component/tablefilter/table_filter.dart';
import 'package:WebAngular/src/directives/highlight.dart';

import 'package:WebAngular/src/domain/booky.dart';
import 'package:WebAngular/src/domain/word.dart';
import 'package:WebAngular/src/services/mock_client.dart';
import 'package:WebAngular/src/services/word_http_service.dart';
import 'package:angular/angular.dart';
//import 'package:angular_components/angular_components.dart';
import 'package:bootjack/bootjack.dart';
// AngularDart info: https://webdev.dartlang.org/angular
// Components info: https://webdev.dartlang.org/components

@Component(
  selector: 'my-app',
  styleUrls: const ['app_component.css'],
  templateUrl: 'app_component.html',
  directives: const [
   // materialDirectives,
    HighlightDirective,
    CORE_DIRECTIVES,
    Booky,
    BookTable,
    TableFilter,
    EditableField,
    MainLayout
  ],
  providers: const [/*materialProviders,*/ WordHttpService],
)
class AppComponent implements OnInit {



  List<Word> wordList;
  String word;
  final WordHttpService _wordHttpService;
  List<Booky> mockBookList = [
    new Booky.createBook("Java in action", ["Karl Mellown"], "83298377394",
        new DateTime(2017), "God book"),
    new Booky.createBook("Kotlin in action", ["Andrey Jemerov"], "123298377394",
        new DateTime(2017), "Best book"),
    new Booky.createBook("Javasript", ["Kishori Sharan"], "123298377394",
        new DateTime(2017), "Best book"),
    new Booky.createBook("Maven recipes", ["Andrey Jemerov"], "123298377394",
        new DateTime(2017), "Best book"),
    new Booky.createBook("Spring in action", ["Andrey Jemerov"], "123298377394",
        new DateTime(2017), "Best book"),
    new Booky.createBook("EJB in action", ["Andrey Jemerov"], "123298377394",
        new DateTime(2017), "Best book")
  ];
  List<String> headers = [
    "№",
    "Name",
    'Authors',
    "ISBN",
    "Date",
    "Description"
  ];

  AppComponent(this._wordHttpService);

  @override
  ngOnInit() async {
////    print(_wordHttpService);
////    wordList = await getWords();
////    print(wordList);
////    print(wordList.elementAt(0));
//    Dropdown.use();
  }

//  Future<List<Word>> getWords() async {
//    return await _wordHttpService.getWords('word/book/all-tokens');
//  }
//
//  Future<String> getWord() async {
//    return await _wordHttpService.getWord('word/book/all-tokens');
//  }
}
