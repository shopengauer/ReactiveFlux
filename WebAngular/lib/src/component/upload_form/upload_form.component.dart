import 'dart:html';
import 'package:WebAngular/src/services/upload_file_http_service.dart';
import 'package:angular/angular.dart';

/**
 *
 *
 */
@Component(
    selector: "upload-file",
    templateUrl: "upload_form.html",
    providers: const [UploadFileHttpService])
class UploadForm {

  final UploadFileHttpService _uploadHttpService;

  UploadForm(this._uploadHttpService);

  void submitForm(FileList filesFromHtml) {
    List<DomainFile> domainFiles = filesFromHtml.map((File file) => new DomainFile(file)).toList();
    _uploadHttpService.uploadFile(domainFiles);
  }

}

class DomainFile{

  final File _file;

  DomainFile(this._file);

  String get name => _file.name;
  Blob get blob => _file.slice();
}
