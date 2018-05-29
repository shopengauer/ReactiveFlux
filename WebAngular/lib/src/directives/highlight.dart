import 'dart:html';
import 'package:angular/angular.dart';

@Directive(selector: '[myhighlight]')
class HighlightDirective {
  final Element _el;

  HighlightDirective(this._el);

  @HostListener('mouseenter')
  void onMouseEnter() {
    _el.style.backgroundColor = '#CAC4C4';
  }

  @HostListener('dblclick')
  void onDbClick() {
    _el.style.backgroundColor = 'red';
  }

  @HostListener('mouseleave')
  void onMouseLeave() {
    _el.style.backgroundColor = null;
  }
}
