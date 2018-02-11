import 'dart:async';
//import 'dart:io';
import 'dart:html' ;
//mport 'dart:html';

import 'package:angular/angular.dart';
//import 'package:http/http.dart';

@Injectable()
class UploadFileHttpService{

  final Map<String,String> headers = {};
  final String _url = "/upload";

//  UploadHttpService(this._http);

  Future<List> uploadFile(String fileName, Blob blob) async {
       HttpRequest httpRequest = new HttpRequest();
       httpRequest.open('POST', _url);
 //   httpRequest.setRequestHeader("Access-Control-Allow-Origin", "*");
      FormData formData = new FormData()
       ..appendBlob(fileName, blob);
   
    print('${blob.size}');

   httpRequest.send(formData);

   //request = new HttpRequestUpl();


  //  var url = Uri.parse(_url);
  //  print('$path');
  //  var multiPartFile = await MultipartFile.fromPath("file", path);

 //   var request = new MultipartRequest("POST", url);
 //   request.files.add(multiPartFile);


//    request.send().then((response) {
//      if (response.statusCode == 200) print("Uploaded!");
//       return response;
//    });


    return null;

  }


}
