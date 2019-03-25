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

r_m_i_connector_CREDENTIALS(OUT) :- 
	object_get('javax.management.remote.rmi.RMIConnector', credentials, OUT).

r_m_i_connector(ARG0, ARG1, OUT) :- 
	object_new('javax.management.remote.rmi.RMIConnector', [ARG0, ARG1], OUT).

r_m_i_connector(ARG0, ARG1, OUT) :- 
	object_new('javax.management.remote.rmi.RMIConnector', [ARG0, ARG1], OUT).

r_m_i_connector_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

r_m_i_connector_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

r_m_i_connector_close(REF) :- 
	object_call(REF, close, [], _).

r_m_i_connector_remove_connection_notification_listener(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, removeConnectionNotificationListener, [ARG0, ARG1, ARG2], _).

r_m_i_connector_get_address(REF, OUT) :- 
	object_call(REF, getAddress, [], OUT).

r_m_i_connector_notify(REF) :- 
	object_call(REF, notify, [], _).

r_m_i_connector_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

r_m_i_connector_remove_connection_notification_listener(REF, ARG0) :- 
	object_call(REF, removeConnectionNotificationListener, [ARG0], _).

r_m_i_connector_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

r_m_i_connector_get_m_bean_server_connection(REF, ARG0, OUT) :- 
	object_call(REF, getMBeanServerConnection, [ARG0], OUT).

r_m_i_connector_get_m_bean_server_connection(REF, OUT) :- 
	object_call(REF, getMBeanServerConnection, [], OUT).

r_m_i_connector_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

r_m_i_connector_connect(REF, ARG0) :- 
	object_call(REF, connect, [ARG0], _).

r_m_i_connector_connect(REF) :- 
	object_call(REF, connect, [], _).

r_m_i_connector_get_connection_id(REF, OUT) :- 
	object_call(REF, getConnectionId, [], OUT).

r_m_i_connector_add_connection_notification_listener(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, addConnectionNotificationListener, [ARG0, ARG1, ARG2], _).

r_m_i_connector_wait(REF) :- 
	object_call(REF, wait, [], _).

r_m_i_connector_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

r_m_i_connector_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

