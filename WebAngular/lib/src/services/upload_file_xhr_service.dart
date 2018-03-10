import 'dart:async';
import 'dart:html';
import 'package:WebAngular/src/component/upload_form/upload_form.component.dart';
import 'package:angular/angular.dart';


@Injectable()
class UploadFileXhrService {
  final Map<String, String> headers = {};
  final String _url = "/upload";

  Future<List> uploadFile(List<DomainFile> domainFiles) async {
    HttpRequest httpRequest = new HttpRequest();
    httpRequest.open('POST', _url);
    //   httpRequest.setRequestHeader("Access-Control-Allow-Origin", "*");
    FormData formData = new FormData();
    domainFiles.forEach((file) => formData.appendBlob(file.name, file.blob));
  //  httpRequest.onReadyStateChange.listen(onData) =
    httpRequest.send(formData);

    return null;
  }
}
