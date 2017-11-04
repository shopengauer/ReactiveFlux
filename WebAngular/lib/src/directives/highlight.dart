import 'dart:html';
import 'package:angular/angular.dart';


@Directive(selector: '[myhighlight]')
class HighlightDirective{

  final Element _el;


  HighlightDirective(this._el);

  @HostListener('mouseenter')
  void onMouseEnter(){
     _el.style.backgroundColor = 'grey';
  }

  @HostListener('mouseleave')
  void onMouseLeave(){
     _el.style.backgroundColor = null;
  }

}