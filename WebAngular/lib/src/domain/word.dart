

/**
 *   Класс представляючий слово на определенном языке
 *   с перечнем переводов
 */

class Word {

  String token;
  Language language;
  String lang;
  List<String> translates;


  Word();

  Word.createWord(this.token, this.lang);

  Word.createWordWithTranslates(this.token, this.lang, this.translates);

  @override
  String toString() {
    return 'Word{token: $token, language: $lang, translates: $translates}';
  }




}

enum Language {


  ENGLISH,
  RUSSIAN
}

const String english = 'English';
const String russian = 'Russian';