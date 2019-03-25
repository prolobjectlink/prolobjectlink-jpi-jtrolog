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

j_trolog_clause_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

j_trolog_clause_unify(REF, ARG0, OUT) :- 
	object_call(REF, unify, [ARG0], OUT).

j_trolog_clause_notify(REF) :- 
	object_call(REF, notify, [], _).

j_trolog_clause_wait(REF) :- 
	object_call(REF, wait, [], _).

j_trolog_clause_get_body_iterator(REF, OUT) :- 
	object_call(REF, getBodyIterator, [], OUT).

j_trolog_clause_is_fact(REF, OUT) :- 
	object_call(REF, isFact, [], OUT).

j_trolog_clause_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

j_trolog_clause_get_term(REF, OUT) :- 
	object_call(REF, getTerm, [], OUT).

j_trolog_clause_is_directive(REF, OUT) :- 
	object_call(REF, isDirective, [], OUT).

j_trolog_clause_is_dynamic(REF, OUT) :- 
	object_call(REF, isDynamic, [], OUT).

j_trolog_clause_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

j_trolog_clause_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

j_trolog_clause_get_head(REF, OUT) :- 
	object_call(REF, getHead, [], OUT).

j_trolog_clause_get_body_array(REF, OUT) :- 
	object_call(REF, getBodyArray, [], OUT).

j_trolog_clause_get_functor(REF, OUT) :- 
	object_call(REF, getFunctor, [], OUT).

j_trolog_clause_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

j_trolog_clause_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

j_trolog_clause_get_arity(REF, OUT) :- 
	object_call(REF, getArity, [], OUT).

j_trolog_clause_is_rule(REF, OUT) :- 
	object_call(REF, isRule, [], OUT).

j_trolog_clause_is_multifile(REF, OUT) :- 
	object_call(REF, isMultifile, [], OUT).

j_trolog_clause_is_discontiguous(REF, OUT) :- 
	object_call(REF, isDiscontiguous, [], OUT).

j_trolog_clause_get_body(REF, OUT) :- 
	object_call(REF, getBody, [], OUT).

j_trolog_clause_get_indicator(REF, OUT) :- 
	object_call(REF, getIndicator, [], OUT).

j_trolog_clause_get_prolog_indicator(REF, OUT) :- 
	object_call(REF, getPrologIndicator, [], OUT).

j_trolog_clause_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

