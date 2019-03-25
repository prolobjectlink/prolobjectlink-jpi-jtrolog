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

atomic_long(OUT) :- 
	object_new('java.util.concurrent.atomic.AtomicLong', [], OUT).

atomic_long(ARG0, OUT) :- 
	object_new('java.util.concurrent.atomic.AtomicLong', [ARG0], OUT).

atomic_long_compare_and_set(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, compareAndSet, [ARG0, ARG1], OUT).

atomic_long_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

atomic_long_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

atomic_long_get(REF, OUT) :- 
	object_call(REF, get, [], OUT).

atomic_long_set(REF, ARG0) :- 
	object_call(REF, set, [ARG0], _).

atomic_long_get_and_update(REF, ARG0, OUT) :- 
	object_call(REF, getAndUpdate, [ARG0], OUT).

atomic_long_short_value(REF, OUT) :- 
	object_call(REF, shortValue, [], OUT).

atomic_long_int_value(REF, OUT) :- 
	object_call(REF, intValue, [], OUT).

atomic_long_get_and_decrement(REF, OUT) :- 
	object_call(REF, getAndDecrement, [], OUT).

atomic_long_notify(REF) :- 
	object_call(REF, notify, [], _).

atomic_long_get_and_set(REF, ARG0, OUT) :- 
	object_call(REF, getAndSet, [ARG0], OUT).

atomic_long_get_and_add(REF, ARG0, OUT) :- 
	object_call(REF, getAndAdd, [ARG0], OUT).

atomic_long_weak_compare_and_set(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, weakCompareAndSet, [ARG0, ARG1], OUT).

atomic_long_get_and_increment(REF, OUT) :- 
	object_call(REF, getAndIncrement, [], OUT).

atomic_long_double_value(REF, OUT) :- 
	object_call(REF, doubleValue, [], OUT).

atomic_long_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

atomic_long_increment_and_get(REF, OUT) :- 
	object_call(REF, incrementAndGet, [], OUT).

atomic_long_long_value(REF, OUT) :- 
	object_call(REF, longValue, [], OUT).

atomic_long_lazy_set(REF, ARG0) :- 
	object_call(REF, lazySet, [ARG0], _).

atomic_long_add_and_get(REF, ARG0, OUT) :- 
	object_call(REF, addAndGet, [ARG0], OUT).

atomic_long_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

atomic_long_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

atomic_long_wait(REF) :- 
	object_call(REF, wait, [], _).

atomic_long_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

atomic_long_decrement_and_get(REF, OUT) :- 
	object_call(REF, decrementAndGet, [], OUT).

atomic_long_byte_value(REF, OUT) :- 
	object_call(REF, byteValue, [], OUT).

atomic_long_float_value(REF, OUT) :- 
	object_call(REF, floatValue, [], OUT).

atomic_long_update_and_get(REF, ARG0, OUT) :- 
	object_call(REF, updateAndGet, [ARG0], OUT).

atomic_long_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

atomic_long_get_and_accumulate(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, getAndAccumulate, [ARG0, ARG1], OUT).

atomic_long_accumulate_and_get(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, accumulateAndGet, [ARG0, ARG1], OUT).

