import ""

@Component(){
selector: "book-table",
tamplateUrl: "book-table.html",
directives: const [CORE_DIRECTIVES]

}
class BookTable{

@Input
List<Booky> bookList;

@Input
List<String> headers;

}
