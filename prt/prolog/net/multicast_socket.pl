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

multicast_socket(ARG0, OUT) :- 
	object_new('java.net.MulticastSocket', [ARG0], OUT).

multicast_socket(ARG0, OUT) :- 
	object_new('java.net.MulticastSocket', [ARG0], OUT).

multicast_socket(OUT) :- 
	object_new('java.net.MulticastSocket', [], OUT).

multicast_socket_set_reuse_address(REF, ARG0) :- 
	object_call(REF, setReuseAddress, [ARG0], _).

multicast_socket_set_datagram_socket_impl_factory(REF, ARG0) :- 
	object_call(REF, setDatagramSocketImplFactory, [ARG0], _).

multicast_socket_get_broadcast(REF, OUT) :- 
	object_call(REF, getBroadcast, [], OUT).

multicast_socket_connect(REF, ARG0) :- 
	object_call(REF, connect, [ARG0], _).

multicast_socket_get_channel(REF, OUT) :- 
	object_call(REF, getChannel, [], OUT).

multicast_socket_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

multicast_socket_get_receive_buffer_size(REF, OUT) :- 
	object_call(REF, getReceiveBufferSize, [], OUT).

multicast_socket_connect(REF, ARG0, ARG1) :- 
	object_call(REF, connect, [ARG0, ARG1], _).

multicast_socket_get_network_interface(REF, OUT) :- 
	object_call(REF, getNetworkInterface, [], OUT).

multicast_socket_set_so_timeout(REF, ARG0) :- 
	object_call(REF, setSoTimeout, [ARG0], _).

multicast_socket_get_send_buffer_size(REF, OUT) :- 
	object_call(REF, getSendBufferSize, [], OUT).

multicast_socket_bind(REF, ARG0) :- 
	object_call(REF, bind, [ARG0], _).

multicast_socket_get_traffic_class(REF, OUT) :- 
	object_call(REF, getTrafficClass, [], OUT).

multicast_socket_leave_group(REF, ARG0) :- 
	object_call(REF, leaveGroup, [ARG0], _).

multicast_socket_set_traffic_class(REF, ARG0) :- 
	object_call(REF, setTrafficClass, [ARG0], _).

multicast_socket_get_local_port(REF, OUT) :- 
	object_call(REF, getLocalPort, [], OUT).

multicast_socket_leave_group(REF, ARG0, ARG1) :- 
	object_call(REF, leaveGroup, [ARG0, ARG1], _).

multicast_socket_get_time_to_live(REF, OUT) :- 
	object_call(REF, getTimeToLive, [], OUT).

multicast_socket_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

multicast_socket_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

multicast_socket_disconnect(REF) :- 
	object_call(REF, disconnect, [], _).

multicast_socket_get_loopback_mode(REF, OUT) :- 
	object_call(REF, getLoopbackMode, [], OUT).

multicast_socket_get_local_address(REF, OUT) :- 
	object_call(REF, getLocalAddress, [], OUT).

multicast_socket_send(REF, ARG0) :- 
	object_call(REF, send, [ARG0], _).

multicast_socket_get_remote_socket_address(REF, OUT) :- 
	object_call(REF, getRemoteSocketAddress, [], OUT).

multicast_socket_send(REF, ARG0, ARG1) :- 
	object_call(REF, send, [ARG0, ARG1], _).

multicast_socket_notify(REF) :- 
	object_call(REF, notify, [], _).

multicast_socket_set_t_t_l(REF, ARG0) :- 
	object_call(REF, setTTL, [ARG0], _).

multicast_socket_get_interface(REF, OUT) :- 
	object_call(REF, getInterface, [], OUT).

multicast_socket_receive(REF, ARG0) :- 
	object_call(REF, receive, [ARG0], _).

multicast_socket_wait(REF) :- 
	object_call(REF, wait, [], _).

multicast_socket_set_send_buffer_size(REF, ARG0) :- 
	object_call(REF, setSendBufferSize, [ARG0], _).

multicast_socket_set_time_to_live(REF, ARG0) :- 
	object_call(REF, setTimeToLive, [ARG0], _).

multicast_socket_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

multicast_socket_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

multicast_socket_get_inet_address(REF, OUT) :- 
	object_call(REF, getInetAddress, [], OUT).

multicast_socket_set_loopback_mode(REF, ARG0) :- 
	object_call(REF, setLoopbackMode, [ARG0], _).

multicast_socket_set_network_interface(REF, ARG0) :- 
	object_call(REF, setNetworkInterface, [ARG0], _).

multicast_socket_is_bound(REF, OUT) :- 
	object_call(REF, isBound, [], OUT).

multicast_socket_is_closed(REF, OUT) :- 
	object_call(REF, isClosed, [], OUT).

multicast_socket_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

multicast_socket_join_group(REF, ARG0, ARG1) :- 
	object_call(REF, joinGroup, [ARG0, ARG1], _).

multicast_socket_join_group(REF, ARG0) :- 
	object_call(REF, joinGroup, [ARG0], _).

multicast_socket_get_t_t_l(REF, OUT) :- 
	object_call(REF, getTTL, [], OUT).

multicast_socket_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

multicast_socket_set_interface(REF, ARG0) :- 
	object_call(REF, setInterface, [ARG0], _).

multicast_socket_get_local_socket_address(REF, OUT) :- 
	object_call(REF, getLocalSocketAddress, [], OUT).

multicast_socket_get_port(REF, OUT) :- 
	object_call(REF, getPort, [], OUT).

multicast_socket_close(REF) :- 
	object_call(REF, close, [], _).

multicast_socket_set_receive_buffer_size(REF, ARG0) :- 
	object_call(REF, setReceiveBufferSize, [ARG0], _).

multicast_socket_is_connected(REF, OUT) :- 
	object_call(REF, isConnected, [], OUT).

multicast_socket_get_reuse_address(REF, OUT) :- 
	object_call(REF, getReuseAddress, [], OUT).

multicast_socket_get_so_timeout(REF, OUT) :- 
	object_call(REF, getSoTimeout, [], OUT).

multicast_socket_set_broadcast(REF, ARG0) :- 
	object_call(REF, setBroadcast, [ARG0], _).
