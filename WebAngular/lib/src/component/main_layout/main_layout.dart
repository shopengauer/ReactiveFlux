import 'package:WebAngular/src/component/navbar/navbar.dart';
import 'package:WebAngular/src/component/upload_form/upload_form.component.dart';
import 'package:angular/angular.dart';


@Component(
  selector: "main-layout",
  templateUrl: "main_layout.html",
  directives: const [CORE_DIRECTIVES, NavBar, UploadForm]
)
class MainLayout{



}