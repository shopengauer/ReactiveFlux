import 'dart:async';
import 'package:WebAngular/src/domain/word.dart';
import 'package:angular/angular.dart';
import 'package:http/http.dart';
import 'package:jsonx/jsonx.dart';
import 'dart:convert';

@Injectable()
class WordHttpService {

  final Client _client;

  WordHttpService(this._client);

  Future<List<Word>> getWords(String url) async {
    final response = await _client.get(url);
    return decode(response.body);
  }

  Future<String> getWord(String url) async {
    final response = await _client.get(url);
    return decode(response.body);

  }


}