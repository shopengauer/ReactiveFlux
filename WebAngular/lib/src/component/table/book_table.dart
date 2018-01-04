import 'dart:html';
import 'package:WebAngular/src/component/editablefield/editable_field.dart';
import 'package:WebAngular/src/directives/highlight.dart';
import 'package:WebAngular/src/domain/booky.dart';
import "package:angular/angular.dart";

@Component(
    selector: "book-table",
    templateUrl: "book_table.html",
    directives: const [CORE_DIRECTIVES, HighlightDirective, EditableField])
class BookTable implements OnInit, AfterViewChecked {
  /**
   * Признак что включен режим редактирования произвольного поля таблицы
   */
  bool isEdit;

  @Input()
  List<Booky> bookList;

  @Input()
  List<String> headers;

  List<Booky> bookListCopy;

  @override
  ngOnInit() {
    bookListCopy = new List.from(bookList);
    isEdit = false;
  }

  editField(String e){
    print("Edit string $e");
  }


  String getIndex(int i) => 'id$i';
//  List<Booky> get bookListCopy => new List.from(bookList);
//  void set bookListCopy(List<Booky> bookListCopy) => this.bookListCopy = bookListCopy;

  @override
  ngAfterViewChecked() {
  //  print(bookListCopy);
    // TODO: implement ngAfterViewChecked
  }
}
