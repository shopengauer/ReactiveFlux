import 'package:WebAngular/src/domain/word.dart';


var mockWordSet = [
  new Word.createWordWithTranslates("pencil", english, ['карандаш']),
  new Word.createWordWithTranslates("pen", english, ['ручка']),
  new Word.createWordWithTranslates("hello", english, ['привет'])
];

var mockWordSetJson = [
  {"token":"pencil","language":0,"translates":["ashdjshdjsh"]},{"token":"pen","language":0,"translates":["жопа"]},{"token":"hello","language":0,"translates":[""]}
];



var mockResponse = {
  "list": {
    "FOREWORD": 1,
    "actionlicensed": 1,
    "SHELTER": 1,
    "ISLANDLicensed": 1,
    "discounts": 1,
    "ordered": 1,
    "quantity": 1,
    "sales": 1,
    "department": 1,
    "orders": 1,
    "rights": 1,
    "publication": 1}
};