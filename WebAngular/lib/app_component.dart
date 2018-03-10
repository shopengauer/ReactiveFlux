import 'dart:async';
import 'package:WebAngular/src/component/editablefield/editable_field.dart';
import 'package:WebAngular/src/component/main_layout/main_layout.dart';
import 'package:WebAngular/src/component/table/book_table.dart';
import 'package:WebAngular/src/component/tablefilter/table_filter.dart';
import 'package:WebAngular/src/directives/highlight.dart';
import 'package:WebAngular/src/domain/booky.dart';
import 'package:WebAngular/src/services/word_http_service.dart';
import 'package:angular/angular.dart';
import 'package:bootjack/bootjack.dart';


@Component(
  selector: 'my-app',
  styleUrls: const ['app_component.css'],
  templateUrl: 'app_component.html',
  directives: const [
    HighlightDirective,
    CORE_DIRECTIVES,
    Booky,
    BookTable,
    TableFilter,
    EditableField,
    MainLayout
  ],
  providers: const [WordHttpService],
)
class AppComponent implements OnInit {


  @override
  ngOnInit() async {

  }

}
