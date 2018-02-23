import 'dart:html';
//import 'dart:io';

import 'package:WebAngular/src/services/upload_file_http_service.dart';
import 'package:angular/angular.dart';

@Component(
  selector: "upload-file",
  templateUrl: "upload_form.html",
  providers: const [UploadFileHttpService]

)
class UploadForm implements OnChanges{

  final UploadFileHttpService _uploadHttpService;


  UploadForm(this._uploadHttpService);

  void submitForm(files){
     File file = files[0];
     FileReader fileReader = new FileReader();
     fileReader.readAsArrayBuffer(file.slice());
     //fileReader.onLoad = load;
    _uploadHttpService.uploadFile(file.name,file.slice());


  //     Blob blob = file.slice();

//   FormData formData = new FormData();
//   formData.appendBlob(file.name, blob);
//
//   print('${blob.size}');
//    print('${files[0].type}');
//
//    print('${files}');files
  }


  onInit(){
   // FormElement v = new FormElement();
   // v.
  }

  @override
  ngOnChanges(Map<String, SimpleChange> changes) {

  }

}