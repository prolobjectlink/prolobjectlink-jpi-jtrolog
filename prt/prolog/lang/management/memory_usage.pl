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

memory_usage(ARG0, ARG1, ARG2, ARG3, OUT) :- 
	object_new('java.lang.management.MemoryUsage', [ARG0, ARG1, ARG2, ARG3], OUT).

memory_usage_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

memory_usage_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

memory_usage_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

memory_usage_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

memory_usage_from(REF, ARG0, OUT) :- 
	object_call(REF, from, [ARG0], OUT).

memory_usage_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

memory_usage_get_used(REF, OUT) :- 
	object_call(REF, getUsed, [], OUT).

memory_usage_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

memory_usage_wait(REF) :- 
	object_call(REF, wait, [], _).

memory_usage_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

memory_usage_notify(REF) :- 
	object_call(REF, notify, [], _).

memory_usage_get_max(REF, OUT) :- 
	object_call(REF, getMax, [], OUT).

memory_usage_get_committed(REF, OUT) :- 
	object_call(REF, getCommitted, [], OUT).

memory_usage_get_init(REF, OUT) :- 
	object_call(REF, getInit, [], OUT).

