import 'dart:async';
import 'dart:convert';

import 'package:WebAngular/src/services/mock_word_set.dart';
import 'package:angular/angular.dart';
import 'package:http/http.dart';
import 'package:http/testing.dart';
//import 'package:jsonx/jsonx.dart';

@Injectable()
class WordMockClient extends MockClient {

  String _name;


  static Future<Response> _handler(Request request) async {


    switch (request.method) {
      case 'GET':
        print(request.url.path);
        break;
    }

  //  print(mockWordSet.elementAt(0));
  //  print(encode(mockWordSet));

    //return new Response(JSON.encode(mockResponse), 200);
   // return new Response("zopaф", 200);
   // return new Response(JSON.encode("Русский"), 200, headers: {"content-type" : "text/html; charset=utf-8"});
    return new Response(null, 200, headers: {"content-type" : "text/html; charset=utf-8"});
  //  return new Response("sdsfsd", 200);

  }

  WordMockClient()
      : _name="MockServiceClass",
        super(_handler);


}
