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

j_trolog_logger_RUNTIME_ERROR(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', runtime_error, OUT).

j_trolog_logger_FILE_NOT_FOUND(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', file_not_found, OUT).

j_trolog_logger_CLASS_NOT_FOUND(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', class_not_found, OUT).

j_trolog_logger_UNKNOW_PREDICATE(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', unknow_predicate, OUT).

j_trolog_logger_SYNTAX_ERROR(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', syntax_error, OUT).

j_trolog_logger_NON_SOLUTION(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', non_solution, OUT).

j_trolog_logger_INDICATOR_NOT_FOUND(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', indicator_not_found, OUT).

j_trolog_logger_IO(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', io, OUT).

j_trolog_logger_ERROR_LOADING_BUILT_INS(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', error_loading_built_ins, OUT).

j_trolog_logger_DONT_WORRY(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', dont_worry, OUT).

j_trolog_logger_INTERRUPTED_ERROR(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', interrupted_error, OUT).

j_trolog_logger_EXECUTION_ERROR(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', execution_error, OUT).

j_trolog_logger_FILE_NOT_DELETE(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', file_not_delete, OUT).

j_trolog_logger_INSTANTIATION(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', instantiation, OUT).

j_trolog_logger_ILLEGAL_ACCESS(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', illegal_access, OUT).

j_trolog_logger_NO_SUCH_METHOD(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', no_such_method, OUT).

j_trolog_logger_SECURITY(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', security, OUT).

j_trolog_logger_SQL_ERROR(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', sql_error, OUT).

j_trolog_logger_UNKNOWN_HOST(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', unknown_host, OUT).

j_trolog_logger_ILLEGAL_ARGUMENT(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', illegal_argument, OUT).

j_trolog_logger_INVOCATION_TARGET(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', invocation_target, OUT).

j_trolog_logger_NO_SUCH_FIELD(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', no_such_field, OUT).

j_trolog_logger_CLASS_CAST(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', class_cast, OUT).

j_trolog_logger_URI(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', uri, OUT).

j_trolog_logger_URL(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', url, OUT).

j_trolog_logger_LINK(OUT) :- 
	object_get('org.prolobjectlink.prolog.jtrolog.JTrologLogger', link, OUT).

j_trolog_logger(OUT) :- 
	object_new('org.prolobjectlink.prolog.jtrolog.JTrologLogger', [], OUT).

j_trolog_logger(ARG0, OUT) :- 
	object_new('org.prolobjectlink.prolog.jtrolog.JTrologLogger', [ARG0], OUT).

j_trolog_logger_debug(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, debug, [ARG0, ARG1, ARG2], _).

j_trolog_logger_error(REF, ARG0, ARG1) :- 
	object_call(REF, error, [ARG0, ARG1], _).

j_trolog_logger_debug(REF, ARG0, ARG1) :- 
	object_call(REF, debug, [ARG0, ARG1], _).

j_trolog_logger_warn(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, warn, [ARG0, ARG1, ARG2], _).

j_trolog_logger_warn(REF, ARG0, ARG1) :- 
	object_call(REF, warn, [ARG0, ARG1], _).

j_trolog_logger_error(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, error, [ARG0, ARG1, ARG2], _).

j_trolog_logger_notify(REF) :- 
	object_call(REF, notify, [], _).

j_trolog_logger_trace(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, trace, [ARG0, ARG1, ARG2], _).

j_trolog_logger_trace(REF, ARG0, ARG1) :- 
	object_call(REF, trace, [ARG0, ARG1], _).

j_trolog_logger_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

j_trolog_logger_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

j_trolog_logger_wait(REF) :- 
	object_call(REF, wait, [], _).

j_trolog_logger_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

j_trolog_logger_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

j_trolog_logger_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

j_trolog_logger_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

j_trolog_logger_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

j_trolog_logger_info(REF, ARG0, ARG1) :- 
	object_call(REF, info, [ARG0, ARG1], _).

j_trolog_logger_log(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, log, [ARG0, ARG1, ARG2], _).

j_trolog_logger_log(REF, ARG0, ARG1, ARG2, ARG3) :- 
	object_call(REF, log, [ARG0, ARG1, ARG2, ARG3], _).

j_trolog_logger_info(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, info, [ARG0, ARG1, ARG2], _).

