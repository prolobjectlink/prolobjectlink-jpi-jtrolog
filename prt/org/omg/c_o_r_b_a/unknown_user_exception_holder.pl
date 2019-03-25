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

:-include('../../../../obj/prolobject.pl').

unknown_user_exception_holder(ARG0, OUT) :- 
	object_new('org.omg.CORBA.UnknownUserExceptionHolder', [ARG0], OUT).

unknown_user_exception_holder(OUT) :- 
	object_new('org.omg.CORBA.UnknownUserExceptionHolder', [], OUT).

unknown_user_exception_holder_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

unknown_user_exception_holder_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

unknown_user_exception_holder__write(REF, ARG0) :- 
	object_call(REF, '_write', [ARG0], _).

unknown_user_exception_holder__read(REF, ARG0) :- 
	object_call(REF, '_read', [ARG0], _).

unknown_user_exception_holder_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

unknown_user_exception_holder__type(REF, OUT) :- 
	object_call(REF, '_type', [], OUT).

unknown_user_exception_holder_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

unknown_user_exception_holder_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

unknown_user_exception_holder_wait(REF) :- 
	object_call(REF, wait, [], _).

unknown_user_exception_holder_notify(REF) :- 
	object_call(REF, notify, [], _).

unknown_user_exception_holder_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

unknown_user_exception_holder_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

