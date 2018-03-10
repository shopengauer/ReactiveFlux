import 'dart:html';
import 'package:WebAngular/src/services/upload_file_xhr_service.dart';
import 'package:angular/angular.dart';

@Component(
    selector: "upload-file",
    templateUrl: "upload_form.html",
    providers: const [UploadFileXhrService])
class UploadForm {

  final UploadFileXhrService _uploadXhrService;

  UploadForm(this._uploadXhrService);

  /**
   * Загрузка списка файлов посредством стандартновго Ajax механизма
   * XMLHttpRequest
   */
  void submitXhrForm(FileList filesFromHtml) {
    List<DomainFile> domainFiles = filesFromHtml.map((File file) => new DomainFile(file)).toList();
    _uploadXhrService.uploadFile(domainFiles);
  }

 /**
   * Загрузка списка файлов посредством библиотеке http.
   * Скорее всего эту библтотеку нельзя использовать на клиентской стороне
   * только на сервере because MultiPartFile have import dart.io!!!
   */

  /*@deprecated
  void submitHttpForm(FileList filesFromHtml){
    List<DomainFile> domainFiles = filesFromHtml.map((File file) => new DomainFile(file)).toList();
    _uploadFileHttpService.uploadFiles(domainFiles);
  }*/

}

class DomainFile{

  final File _file;

  DomainFile(this._file);

  String get name => _file.name;
  Blob get blob => _file.slice();
  String get relativePath => _file.relativePath;
  File get file => _file;
}
