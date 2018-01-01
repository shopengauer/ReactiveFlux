
import 'dart:html';
import 'package:angular/angular.dart';

@Component(
  selector: "ed-field",
  templateUrl: "editable_field.html",
  directives: const [CORE_DIRECTIVES],

)
class EditableField implements OnInit, OnChanges, AfterViewChecked{

  bool isEdit;

  @Input()
  String fieldValue;

  @ViewChild('textbox')
  ElementRef textbox;

  editText() {
    isEdit = true;
  }

  onEnter(KeyboardEvent event, String inputValue) {
    isEdit = false;
    print(fieldValue.hashCode);
    fieldValue = inputValue;
    print(fieldValue.hashCode);
  }

  onBlur() {
    isEdit = false;
  }

  @override
  ngOnChanges(Map<String, SimpleChange> changes) {
    print('on changes');
    print(changes);
    // TODO: implement ngOnChanges
  }

  @override
  ngOnInit() {
    isEdit = false;
    // TODO: implement ngOnInit
  }


  @override
  ngAfterViewChecked() {
    if(textbox != null){
     /** установка фокуса на input element */
      textbox.nativeElement.focus();
    }

  }
}