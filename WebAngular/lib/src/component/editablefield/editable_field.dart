
import 'dart:async';
import 'dart:html';
import 'package:angular/angular.dart';

@Component(
  selector: "ed-field",
  templateUrl: "editable_field.html",
  directives: const [CORE_DIRECTIVES],

)
class EditableField implements OnInit, OnChanges, AfterViewChecked{

  bool isEdit;
  final _editRequest = new StreamController<String>();

  @Output()
  Stream<String> get editRequest => _editRequest.stream;

  @Input()
  String fieldValue;

  @Input()
  int propIndex;

  @ViewChild('textbox')
  ElementRef textbox;

  editText() {
    isEdit = true;
  }

  onEnter(KeyboardEvent event, String inputValue) {
    isEdit = false;
    print(fieldValue.hashCode);
    fieldValue = inputValue;
    _editRequest.add(inputValue);

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
    // при инициализации поле в нередактируемом состоянии
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