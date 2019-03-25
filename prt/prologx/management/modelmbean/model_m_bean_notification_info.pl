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

model_m_bean_notification_info(ARG0, ARG1, ARG2, ARG3, OUT) :- 
	object_new('javax.management.modelmbean.ModelMBeanNotificationInfo', [ARG0, ARG1, ARG2, ARG3], OUT).

model_m_bean_notification_info(ARG0, OUT) :- 
	object_new('javax.management.modelmbean.ModelMBeanNotificationInfo', [ARG0], OUT).

model_m_bean_notification_info(ARG0, ARG1, ARG2, OUT) :- 
	object_new('javax.management.modelmbean.ModelMBeanNotificationInfo', [ARG0, ARG1, ARG2], OUT).

model_m_bean_notification_info_get_description(REF, OUT) :- 
	object_call(REF, getDescription, [], OUT).

model_m_bean_notification_info_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

model_m_bean_notification_info_clone(REF, OUT) :- 
	object_call(REF, clone, [], OUT).

model_m_bean_notification_info_set_descriptor(REF, ARG0) :- 
	object_call(REF, setDescriptor, [ARG0], _).

model_m_bean_notification_info_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

model_m_bean_notification_info_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

model_m_bean_notification_info_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

model_m_bean_notification_info_get_name(REF, OUT) :- 
	object_call(REF, getName, [], OUT).

model_m_bean_notification_info_wait(REF) :- 
	object_call(REF, wait, [], _).

model_m_bean_notification_info_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

model_m_bean_notification_info_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

model_m_bean_notification_info_get_descriptor(REF, OUT) :- 
	object_call(REF, getDescriptor, [], OUT).

model_m_bean_notification_info_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

model_m_bean_notification_info_get_notif_types(REF, OUT) :- 
	object_call(REF, getNotifTypes, [], OUT).

model_m_bean_notification_info_notify(REF) :- 
	object_call(REF, notify, [], _).

