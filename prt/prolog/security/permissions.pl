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

:-include('../../../obj/prolobject.pl').

permissions(OUT) :- 
	object_new('java.security.Permissions', [], OUT).

permissions_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

permissions_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

permissions_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

permissions_is_read_only(REF, OUT) :- 
	object_call(REF, isReadOnly, [], OUT).

permissions_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

permissions_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

permissions_wait(REF) :- 
	object_call(REF, wait, [], _).

permissions_notify(REF) :- 
	object_call(REF, notify, [], _).

permissions_elements(REF, OUT) :- 
	object_call(REF, elements, [], OUT).

permissions_add(REF, ARG0) :- 
	object_call(REF, add, [ARG0], _).

permissions_set_read_only(REF) :- 
	object_call(REF, setReadOnly, [], _).

permissions_implies(REF, ARG0, OUT) :- 
	object_call(REF, implies, [ARG0], OUT).

permissions_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

permissions_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).
