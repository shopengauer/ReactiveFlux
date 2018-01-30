import 'dart:html';

import 'package:angular/angular.dart';

@Component(
  selector: "upload-file",
  templateUrl: "upload_form.html",

)
class UploadForm implements OnChanges{


  submitForm(files){
   File file = files[0];
   print('${file.size}');;
   Blob blob = file.slice();
   FormData formData = new FormData();
   formData.appendBlob(file.name, blob);

   print('${blob.size}');
    print('${files[0].type}');

    print('${files}');
  }


  onInit(){
    FormElement v = new FormElement();
   // v.
  }

  @override
  ngOnChanges(Map<String, SimpleChange> changes) {

  }

}