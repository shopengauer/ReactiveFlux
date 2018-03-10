import 'package:WebAngular/src/domain/booky.dart';


class TestBooks{

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


}