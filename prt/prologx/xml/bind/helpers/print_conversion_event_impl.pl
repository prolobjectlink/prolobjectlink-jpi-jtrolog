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

print_conversion_event_impl_WARNING(OUT) :- 
	object_get('javax.xml.bind.helpers.PrintConversionEventImpl', warning, OUT).

print_conversion_event_impl_ERROR(OUT) :- 
	object_get('javax.xml.bind.helpers.PrintConversionEventImpl', error, OUT).

print_conversion_event_impl_FATAL_ERROR(OUT) :- 
	object_get('javax.xml.bind.helpers.PrintConversionEventImpl', fatal_error, OUT).

print_conversion_event_impl(ARG0, ARG1, ARG2, ARG3, OUT) :- 
	object_new('javax.xml.bind.helpers.PrintConversionEventImpl', [ARG0, ARG1, ARG2, ARG3], OUT).

print_conversion_event_impl(ARG0, ARG1, ARG2, OUT) :- 
	object_new('javax.xml.bind.helpers.PrintConversionEventImpl', [ARG0, ARG1, ARG2], OUT).

print_conversion_event_impl_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

print_conversion_event_impl_get_severity(REF, OUT) :- 
	object_call(REF, getSeverity, [], OUT).

print_conversion_event_impl_set_severity(REF, ARG0) :- 
	object_call(REF, setSeverity, [ARG0], _).

print_conversion_event_impl_get_linked_exception(REF, OUT) :- 
	object_call(REF, getLinkedException, [], OUT).

print_conversion_event_impl_notify(REF) :- 
	object_call(REF, notify, [], _).

print_conversion_event_impl_set_message(REF, ARG0) :- 
	object_call(REF, setMessage, [ARG0], _).

print_conversion_event_impl_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

print_conversion_event_impl_wait(REF) :- 
	object_call(REF, wait, [], _).

print_conversion_event_impl_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

print_conversion_event_impl_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

print_conversion_event_impl_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

print_conversion_event_impl_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

print_conversion_event_impl_set_linked_exception(REF, ARG0) :- 
	object_call(REF, setLinkedException, [ARG0], _).

print_conversion_event_impl_get_locator(REF, OUT) :- 
	object_call(REF, getLocator, [], OUT).

print_conversion_event_impl_set_locator(REF, ARG0) :- 
	object_call(REF, setLocator, [ARG0], _).

print_conversion_event_impl_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

print_conversion_event_impl_get_message(REF, OUT) :- 
	object_call(REF, getMessage, [], OUT).

