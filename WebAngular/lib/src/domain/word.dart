

/**
 *   Класс представляючий слово на определенном языке
 *   с перечнем переводов
 */

class Word {

  String token;
  Language language;
  List<String> translates;

  Word.createWord(this.token, this.language);

  Word.createWordWithTranslates(this.token, this.language, this.translates);

  @override
  String toString() {
    return 'Word{token: $token, language: $language, translates: $translates}';
  }

  


}

enum Language {
  ENGLISH,
  RUSSIAN
}