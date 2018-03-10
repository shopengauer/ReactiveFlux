import 'dart:html';
import 'package:WebAngular/src/services/upload_file_xhr_service.dart';
import 'package:angular/angular.dart';

@Component(
    selector: "upload-file",
    templateUrl: "upload_form.html",
    directives: const [CORE_DIRECTIVES],
    providers: const [UploadFileXhrService])
class UploadForm implements OnChanges, AfterViewChecked, AfterViewInit, AfterContentChecked{

  /** Ajax service for files upload */
  final UploadFileXhrService _uploadXhrService;

  /** */
  final List<String> fileNames = [];

  UploadForm(this._uploadXhrService);

  /**
   * Загрузка списка файлов посредством стандартновго Ajax механизма
   * XMLHttpRequest
   */
  void submitXhrForm(FileList filesFromHtml) {
    List<DomainFile> domainFiles = filesFromHtml.map((File file) => new DomainFile(file)).toList();
    _uploadXhrService.uploadFile(domainFiles);
  }

  void setFilesName(FileList filesFromHtml){
    fileNames.clear();
    filesFromHtml.forEach((f) => fileNames.add(f.name));
    print('Input click');
  }

  void clearFiles(FileList filesFromHtml){
     filesFromHtml=null;
  }



  @override
  ngOnChanges(Map<String, SimpleChange> changes) {
    print('onChange');
    // TODO: implement ngOnChanges
  }
  @override
  ngAfterViewChecked() {
    print('afterViewChecked');
    // TODO: implement ngAfterViewChecked
  }
  @override
  ngAfterViewInit() {
    print('ngAfterViewInit');
    // TODO: implement ngAfterViewInit
  }
  @override
  ngAfterContentChecked() {
    print('ngAfterContentChecked');
    // TODO: implement ngAfterContentChecked
  }
}

class DomainFile{

  final File _file;

  DomainFile(this._file);

  String get name => _file.name;
  Blob get blob => _file.slice();
  String get relativePath => _file.relativePath;
  File get file => _file;
}
