import 'package:WebAngular/src/component/table/book_table.dart';
import 'package:WebAngular/src/domain/booky.dart';
import 'package:angular/angular.dart';

@Component(
    selector: 'table-filter',
    templateUrl: 'table_filter.html',
    directives: const [BookTable])
class TableFilter {
  @Input()
  BookTable bookTable;

  void filterTable(String value) {
    bookTable.bookListCopy = new List<Booky>.from(bookTable.bookList);
    bookTable.bookListCopy.retainWhere((book) => inputValueTest(book, value));
  }

  bool inputValueTest(Booky book, String value) {
    // print('$book $value');
    return book.authors.contains(value) ||
        book.bookName.toLowerCase().contains(value.toLowerCase()) ||
        book.isbn.toLowerCase().contains(value.toLowerCase()) ||
        book.year.toString().contains(value) ||
        book.description.toLowerCase().contains(value.toLowerCase());
  }
}
