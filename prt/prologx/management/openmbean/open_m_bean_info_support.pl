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

open_m_bean_info_support(ARG0, ARG1, ARG2, ARG3, ARG4, ARG5, OUT) :- 
	object_new('javax.management.openmbean.OpenMBeanInfoSupport', [ARG0, ARG1, ARG2, ARG3, ARG4, ARG5], OUT).

open_m_bean_info_support(ARG0, ARG1, ARG2, ARG3, ARG4, ARG5, ARG6, OUT) :- 
	object_new('javax.management.openmbean.OpenMBeanInfoSupport', [ARG0, ARG1, ARG2, ARG3, ARG4, ARG5, ARG6], OUT).

open_m_bean_info_support_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

open_m_bean_info_support_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

open_m_bean_info_support_get_class_name(REF, OUT) :- 
	object_call(REF, getClassName, [], OUT).

open_m_bean_info_support_get_operations(REF, OUT) :- 
	object_call(REF, getOperations, [], OUT).

open_m_bean_info_support_get_descriptor(REF, OUT) :- 
	object_call(REF, getDescriptor, [], OUT).

open_m_bean_info_support_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

open_m_bean_info_support_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

open_m_bean_info_support_get_description(REF, OUT) :- 
	object_call(REF, getDescription, [], OUT).

open_m_bean_info_support_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

open_m_bean_info_support_get_constructors(REF, OUT) :- 
	object_call(REF, getConstructors, [], OUT).

open_m_bean_info_support_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

open_m_bean_info_support_get_notifications(REF, OUT) :- 
	object_call(REF, getNotifications, [], OUT).

open_m_bean_info_support_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

open_m_bean_info_support_clone(REF, OUT) :- 
	object_call(REF, clone, [], OUT).

open_m_bean_info_support_notify(REF) :- 
	object_call(REF, notify, [], _).

open_m_bean_info_support_get_attributes(REF, OUT) :- 
	object_call(REF, getAttributes, [], OUT).

open_m_bean_info_support_wait(REF) :- 
	object_call(REF, wait, [], _).

