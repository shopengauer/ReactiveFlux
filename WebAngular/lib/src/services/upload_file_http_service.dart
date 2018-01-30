import 'dart:async';
//import 'dart:io';
import 'dart:html' ;
//mport 'dart:html';

import 'package:angular/angular.dart';
//import 'package:http/http.dart';

@Injectable()
class UploadHttpService{

  final Map<String,String> headers = {};
  final _HOST = "127.0.0.1", _PORT = 8080;

  //
 // final Client _http;
  final String _url = "localhost:8080/upload";

//  UploadHttpService(this._http);

  Future<List> uploadFile(String fileName, Blob blob) async {
    Uri uri = new Uri(host: _HOST, port: _PORT);
    HttpRequest httpRequest = new HttpRequest();

   httpRequest.open('POST', uri.toString());
    httpRequest.setRequestHeader("Access-Control-Allow-Origin", "*");
   
   FormData formData = new FormData()
    ..appendBlob(fileName, blob);
   //httpRequest.response;


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