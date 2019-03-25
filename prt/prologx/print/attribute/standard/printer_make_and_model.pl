% Copyright (c) 2019 Prolobjectlink Project

% Permission is hereby granted, free of charge, to any person obtaining a copy
% of this software and associated documentation files (the "Software"), to deal
% in the Software without restriction, including without limitation the rights
% to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
% copies of the Software, and to permit persons to whom the Software is
% furnished to do so, subject to the following conditions:

% The above copyright notice and this permission notice shall be included in
% all copies or substantial portions of the Software.

% THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
% IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
% FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
% AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
% LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
% OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
% THE SOFTWARE.

% Author: Jose Zalacain

:-include('../../../../../obj/prolobject.pl').

printer_make_and_model(ARG0, ARG1, OUT) :- 
	object_new('javax.print.attribute.standard.PrinterMakeAndModel', [ARG0, ARG1], OUT).

printer_make_and_model_notify(REF) :- 
	object_call(REF, notify, [], _).

printer_make_and_model_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

printer_make_and_model_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

printer_make_and_model_get_locale(REF, OUT) :- 
	object_call(REF, getLocale, [], OUT).

printer_make_and_model_get_name(REF, OUT) :- 
	object_call(REF, getName, [], OUT).

printer_make_and_model_get_value(REF, OUT) :- 
	object_call(REF, getValue, [], OUT).

printer_make_and_model_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

printer_make_and_model_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

printer_make_and_model_wait(REF) :- 
	object_call(REF, wait, [], _).

printer_make_and_model_get_category(REF, OUT) :- 
	object_call(REF, getCategory, [], OUT).

printer_make_and_model_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

printer_make_and_model_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

printer_make_and_model_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

