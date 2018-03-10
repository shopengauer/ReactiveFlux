import 'package:WebAngular/src/services/mock_client.dart';
import 'package:angular/angular.dart';

import 'package:WebAngular/app_component.dart';
import 'package:http/http.dart';
import 'package:http/browser_client.dart';

void main() {
   bootstrap(AppComponent,[provide(Client,useFactory: () => new BrowserClient(),deps: [])]);
 // bootstrap(AppComponent,[provide(Client,useClass: WordMockClient)]);
}
