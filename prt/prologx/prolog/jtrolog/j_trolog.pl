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

j_trolog(OUT) :- 
	object_new('org.prolobjectlink.prolog.jtrolog.JTrolog', [], OUT).

j_trolog(ARG0, OUT) :- 
	object_new('org.prolobjectlink.prolog.jtrolog.JTrolog', [ARG0], OUT).

j_trolog_new_engine(REF, OUT) :- 
	object_call(REF, newEngine, [], OUT).

j_trolog_new_engine(REF, ARG0, OUT) :- 
	object_call(REF, newEngine, [ARG0], OUT).

j_trolog_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

j_trolog_to_term_array(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, toTermArray, [ARG0, ARG1], OUT).

j_trolog_from_term(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, fromTerm, [ARG0, ARG1], OUT).

j_trolog_new_structure(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, newStructure, [ARG0, ARG1, ARG2], OUT).

j_trolog_new_structure(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, newStructure, [ARG0, ARG1], OUT).

j_trolog_from_term(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, fromTerm, [ARG0, ARG1, ARG2], OUT).

j_trolog_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

j_trolog_new_atom(REF, ARG0, OUT) :- 
	object_call(REF, newAtom, [ARG0], OUT).

j_trolog_prolog_empty(REF, OUT) :- 
	object_call(REF, prologEmpty, [], OUT).

j_trolog_is_compliant(REF, OUT) :- 
	object_call(REF, isCompliant, [], OUT).

j_trolog_get_logger(REF, OUT) :- 
	object_call(REF, getLogger, [], OUT).

j_trolog_parse_list(REF, ARG0, OUT) :- 
	object_call(REF, parseList, [ARG0], OUT).

j_trolog_new_long(REF, ARG0, OUT) :- 
	object_call(REF, newLong, [ARG0], OUT).

j_trolog_new_long(REF, OUT) :- 
	object_call(REF, newLong, [], OUT).

j_trolog_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

j_trolog_to_term(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, toTerm, [ARG0, ARG1], OUT).

j_trolog_new_float(REF, ARG0, OUT) :- 
	object_call(REF, newFloat, [ARG0], OUT).

j_trolog_prolog_cut(REF, OUT) :- 
	object_call(REF, prologCut, [], OUT).

j_trolog_prolog_false(REF, OUT) :- 
	object_call(REF, prologFalse, [], OUT).

j_trolog_prolog_nil(REF, OUT) :- 
	object_call(REF, prologNil, [], OUT).

j_trolog_prolog_fail(REF, OUT) :- 
	object_call(REF, prologFail, [], OUT).

j_trolog_parse_terms(REF, ARG0, OUT) :- 
	object_call(REF, parseTerms, [ARG0], OUT).

j_trolog_from_term_array(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, fromTermArray, [ARG0, ARG1], OUT).

j_trolog_new_integer(REF, ARG0, OUT) :- 
	object_call(REF, newInteger, [ARG0], OUT).

j_trolog_new_integer(REF, OUT) :- 
	object_call(REF, newInteger, [], OUT).

j_trolog_get_converter(REF, OUT) :- 
	object_call(REF, getConverter, [], OUT).

j_trolog_new_float(REF, OUT) :- 
	object_call(REF, newFloat, [], OUT).

j_trolog_new_variable(REF, ARG0, OUT) :- 
	object_call(REF, newVariable, [ARG0], OUT).

j_trolog_new_variable(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, newVariable, [ARG0, ARG1], OUT).

j_trolog_parse_clause(REF, ARG0, OUT) :- 
	object_call(REF, parseClause, [ARG0], OUT).

j_trolog_parse_structure(REF, ARG0, OUT) :- 
	object_call(REF, parseStructure, [ARG0], OUT).

j_trolog_wait(REF) :- 
	object_call(REF, wait, [], _).

j_trolog_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

j_trolog_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

j_trolog_new_list(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, newList, [ARG0, ARG1], OUT).

j_trolog_new_list(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, newList, [ARG0, ARG1], OUT).

j_trolog_to_term_matrix(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, toTermMatrix, [ARG0, ARG1], OUT).

j_trolog_new_list(REF, ARG0, OUT) :- 
	object_call(REF, newList, [ARG0], OUT).

j_trolog_new_list(REF, ARG0, OUT) :- 
	object_call(REF, newList, [ARG0], OUT).

j_trolog_new_list(REF, OUT) :- 
	object_call(REF, newList, [], OUT).

j_trolog_parse_term(REF, ARG0, OUT) :- 
	object_call(REF, parseTerm, [ARG0], OUT).

j_trolog_prolog_include(REF, ARG0, OUT) :- 
	object_call(REF, prologInclude, [ARG0], OUT).

j_trolog_to_term_map(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, toTermMap, [ARG0, ARG1], OUT).

j_trolog_notify(REF) :- 
	object_call(REF, notify, [], _).

j_trolog_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

j_trolog_get_parser(REF, OUT) :- 
	object_call(REF, getParser, [], OUT).

j_trolog_prolog_true(REF, OUT) :- 
	object_call(REF, prologTrue, [], OUT).

j_trolog_to_term_map_array(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, toTermMapArray, [ARG0, ARG1], OUT).

j_trolog_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

j_trolog_parse_program(REF, ARG0, OUT) :- 
	object_call(REF, parseProgram, [ARG0], OUT).

j_trolog_parse_program(REF, ARG0, OUT) :- 
	object_call(REF, parseProgram, [ARG0], OUT).

j_trolog_new_double(REF, OUT) :- 
	object_call(REF, newDouble, [], OUT).

j_trolog_new_double(REF, ARG0, OUT) :- 
	object_call(REF, newDouble, [ARG0], OUT).

