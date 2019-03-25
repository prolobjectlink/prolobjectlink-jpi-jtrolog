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

delay_queue(ARG0, OUT) :- 
	object_new('java.util.concurrent.DelayQueue', [ARG0], OUT).

delay_queue(OUT) :- 
	object_new('java.util.concurrent.DelayQueue', [], OUT).

delay_queue_take(REF, OUT) :- 
	object_call(REF, take, [], OUT).

delay_queue_take(REF, OUT) :- 
	object_call(REF, take, [], OUT).

delay_queue_add_all(REF, ARG0, OUT) :- 
	object_call(REF, addAll, [ARG0], OUT).

delay_queue_clear(REF) :- 
	object_call(REF, clear, [], _).

delay_queue_to_array(REF, OUT) :- 
	object_call(REF, toArray, [], OUT).

delay_queue_to_array(REF, ARG0, OUT) :- 
	object_call(REF, toArray, [ARG0], OUT).

delay_queue_offer(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, offer, [ARG0, ARG1, ARG2], OUT).

delay_queue_offer(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, offer, [ARG0, ARG1, ARG2], OUT).

delay_queue_retain_all(REF, ARG0, OUT) :- 
	object_call(REF, retainAll, [ARG0], OUT).

delay_queue_poll(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, poll, [ARG0, ARG1], OUT).

delay_queue_poll(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, poll, [ARG0, ARG1], OUT).

delay_queue_offer(REF, ARG0, OUT) :- 
	object_call(REF, offer, [ARG0], OUT).

delay_queue_offer(REF, ARG0, OUT) :- 
	object_call(REF, offer, [ARG0], OUT).

delay_queue_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

delay_queue_is_empty(REF, OUT) :- 
	object_call(REF, isEmpty, [], OUT).

delay_queue_poll(REF, OUT) :- 
	object_call(REF, poll, [], OUT).

delay_queue_poll(REF, OUT) :- 
	object_call(REF, poll, [], OUT).

delay_queue_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

delay_queue_peek(REF, OUT) :- 
	object_call(REF, peek, [], OUT).

delay_queue_peek(REF, OUT) :- 
	object_call(REF, peek, [], OUT).

delay_queue_remaining_capacity(REF, OUT) :- 
	object_call(REF, remainingCapacity, [], OUT).

delay_queue_drain_to(REF, ARG0, OUT) :- 
	object_call(REF, drainTo, [ARG0], OUT).

delay_queue_drain_to(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, drainTo, [ARG0, ARG1], OUT).

delay_queue_contains_all(REF, ARG0, OUT) :- 
	object_call(REF, containsAll, [ARG0], OUT).

delay_queue_parallel_stream(REF, OUT) :- 
	object_call(REF, parallelStream, [], OUT).

delay_queue_add(REF, ARG0, OUT) :- 
	object_call(REF, add, [ARG0], OUT).

delay_queue_add(REF, ARG0, OUT) :- 
	object_call(REF, add, [ARG0], OUT).

delay_queue_size(REF, OUT) :- 
	object_call(REF, size, [], OUT).

delay_queue_put(REF, ARG0) :- 
	object_call(REF, put, [ARG0], _).

delay_queue_put(REF, ARG0) :- 
	object_call(REF, put, [ARG0], _).

delay_queue_spliterator(REF, OUT) :- 
	object_call(REF, spliterator, [], OUT).

delay_queue_remove_if(REF, ARG0, OUT) :- 
	object_call(REF, removeIf, [ARG0], OUT).

delay_queue_remove(REF, OUT) :- 
	object_call(REF, remove, [], OUT).

delay_queue_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

delay_queue_element(REF, OUT) :- 
	object_call(REF, element, [], OUT).

delay_queue_notify(REF) :- 
	object_call(REF, notify, [], _).

delay_queue_for_each(REF, ARG0) :- 
	object_call(REF, forEach, [ARG0], _).

delay_queue_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

delay_queue_wait(REF) :- 
	object_call(REF, wait, [], _).

delay_queue_stream(REF, OUT) :- 
	object_call(REF, stream, [], OUT).

delay_queue_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

delay_queue_remove(REF, ARG0, OUT) :- 
	object_call(REF, remove, [ARG0], OUT).

delay_queue_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

delay_queue_contains(REF, ARG0, OUT) :- 
	object_call(REF, contains, [ARG0], OUT).

delay_queue_iterator(REF, OUT) :- 
	object_call(REF, iterator, [], OUT).

delay_queue_remove_all(REF, ARG0, OUT) :- 
	object_call(REF, removeAll, [ARG0], OUT).

delay_queue_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

