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

u_r_l_class_loader(ARG0, ARG1, ARG2, OUT) :- 
	object_new('java.net.URLClassLoader', [ARG0, ARG1, ARG2], OUT).

u_r_l_class_loader(ARG0, ARG1, OUT) :- 
	object_new('java.net.URLClassLoader', [ARG0, ARG1], OUT).

u_r_l_class_loader(ARG0, OUT) :- 
	object_new('java.net.URLClassLoader', [ARG0], OUT).

u_r_l_class_loader_clear_assertion_status(REF) :- 
	object_call(REF, clearAssertionStatus, [], _).

u_r_l_class_loader_get_system_resource(REF, ARG0, OUT) :- 
	object_call(REF, getSystemResource, [ARG0], OUT).

u_r_l_class_loader_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

u_r_l_class_loader_notify(REF) :- 
	object_call(REF, notify, [], _).

u_r_l_class_loader_get_parent(REF, OUT) :- 
	object_call(REF, getParent, [], OUT).

u_r_l_class_loader_wait(REF) :- 
	object_call(REF, wait, [], _).

u_r_l_class_loader_find_resources(REF, ARG0, OUT) :- 
	object_call(REF, findResources, [ARG0], OUT).

u_r_l_class_loader_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

u_r_l_class_loader_find_resource(REF, ARG0, OUT) :- 
	object_call(REF, findResource, [ARG0], OUT).

u_r_l_class_loader_close(REF) :- 
	object_call(REF, close, [], _).

u_r_l_class_loader_load_class(REF, ARG0, OUT) :- 
	object_call(REF, loadClass, [ARG0], OUT).

u_r_l_class_loader_get_system_class_loader(REF, OUT) :- 
	object_call(REF, getSystemClassLoader, [], OUT).

u_r_l_class_loader_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

u_r_l_class_loader_set_class_assertion_status(REF, ARG0, ARG1) :- 
	object_call(REF, setClassAssertionStatus, [ARG0, ARG1], _).

u_r_l_class_loader_set_default_assertion_status(REF, ARG0) :- 
	object_call(REF, setDefaultAssertionStatus, [ARG0], _).

u_r_l_class_loader_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

u_r_l_class_loader_get_resource(REF, ARG0, OUT) :- 
	object_call(REF, getResource, [ARG0], OUT).

u_r_l_class_loader_get_resource_as_stream(REF, ARG0, OUT) :- 
	object_call(REF, getResourceAsStream, [ARG0], OUT).

u_r_l_class_loader_set_package_assertion_status(REF, ARG0, ARG1) :- 
	object_call(REF, setPackageAssertionStatus, [ARG0, ARG1], _).

u_r_l_class_loader_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

u_r_l_class_loader_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

u_r_l_class_loader_get_system_resource_as_stream(REF, ARG0, OUT) :- 
	object_call(REF, getSystemResourceAsStream, [ARG0], OUT).

u_r_l_class_loader_get_system_resources(REF, ARG0, OUT) :- 
	object_call(REF, getSystemResources, [ARG0], OUT).

u_r_l_class_loader_get_u_r_ls(REF, OUT) :- 
	object_call(REF, getURLs, [], OUT).

u_r_l_class_loader_get_resources(REF, ARG0, OUT) :- 
	object_call(REF, getResources, [ARG0], OUT).

u_r_l_class_loader_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

u_r_l_class_loader_new_instance(REF, ARG0, OUT) :- 
	object_call(REF, newInstance, [ARG0], OUT).

u_r_l_class_loader_new_instance(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, newInstance, [ARG0, ARG1], OUT).

