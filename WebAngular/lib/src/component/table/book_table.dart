import 'package:WebAngular/src/directives/highlight.dart';
import 'package:WebAngular/src/domain/booky.dart';
import "package:angular/angular.dart";

@Component(
    selector: "book-table",
    templateUrl: "book_table.html",
    directives: const [CORE_DIRECTIVES, HighlightDirective])
class BookTable implements OnInit{

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


//  List<Booky> get bookListCopy => new List.from(bookList);
 //  void set bookListCopy(List<Booky> bookListCopy) => this.bookListCopy = bookListCopy;

}
