class Booky {
  String bookName;
  List<String> authors;
  String isbn;
  DateTime year;
  String description;

  Booky.createBook(
      this.bookName, this.authors, this.isbn, this.year, this.description);

  @override
  String toString() {
    return 'Booky{bookName: $bookName, authors: $authors, isbn: $isbn, year: $year, description: $description}';
  }

  Map<String, Object> get bookPropMap => {
        "bookName": bookName,
        "authors": authors.toString(),
        "isbn": isbn,
        "year": year.year.toString(),
        "description": description
      };

  setProperty(int index, String value) {
    switch (index) {
      case 0:
        bookName = value;
        break;
      case 1:
        //authors.add(value);
        break;
      case 2:
        isbn = value;
        break;

      case 3:

        year = new DateTime(int.parse(value));
        break;

      case 4:
        description = value;
        break;

    }
  }
}
