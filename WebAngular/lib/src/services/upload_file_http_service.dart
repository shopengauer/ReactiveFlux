import 'dart:async';

import 'package:angular/angular.dart';
import 'package:http/http.dart';

@Injectable()
class UploadHttpService{


  final Client _http;
  final String url = "localhost:8080/upload";


  UploadHttpService(this._http);



  Future<Response> uploadFile() async {

  //  final response = await _http.post(url, headers: )

    return null;

  }


}