import 'dart:async';
import 'dart:convert';
import 'package:WebAngular/src/domain/word.dart';
import 'package:angular/angular.dart';
import 'package:http/http.dart';
//import 'package:jsonx/jsonx.dart';

@Injectable()
class WordHttpService {

  final Client _client;

  WordHttpService(this._client);

  Future<List<Word>> getWords(String url) async {
    final response = await _client.get(url);
  //  print(response.body);
    List<Map<String,dynamic>> list = JSON.decode(response.body);
    print(list.map(mapFunction));


  //  return decode('[1,2,3]', type: [].runtimeType);
    return list.map(mapFunction).toList();
  }

  Future<String> getWord(String url) async {
    final response = await _client.get(url);
    return null;//decode(response.body);

  }


}

Word mapFunction(Map<String, dynamic> wordMap){

  return new Word.createWordWithTranslates(wordMap['token'], wordMap['lang'], wordMap['translates']);

}