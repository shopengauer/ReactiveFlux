


class Booky{
   String bookName;
   List<String> authors;
   String isbn;
   DateTime year;
   String description;

   Booky.createBook(this.bookName, this.authors, this.isbn, this.year, this.description);

   @override
   String toString() {
      return 'Booky{bookName: $bookName, authors: $authors, isbn: $isbn, year: $year, description: $description}';
   }


}


List<Booky> bookList = [new Booky.createBook("Java in action", ["Karl Mellown"], "83298377394", new DateTime(2017), "God book")];