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

overlay_layout(ARG0, OUT) :- 
	object_new('javax.swing.OverlayLayout', [ARG0], OUT).

overlay_layout_get_target(REF, OUT) :- 
	object_call(REF, getTarget, [], OUT).

overlay_layout_preferred_layout_size(REF, ARG0, OUT) :- 
	object_call(REF, preferredLayoutSize, [ARG0], OUT).

overlay_layout_minimum_layout_size(REF, ARG0, OUT) :- 
	object_call(REF, minimumLayoutSize, [ARG0], OUT).

overlay_layout_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

overlay_layout_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

overlay_layout_layout_container(REF, ARG0) :- 
	object_call(REF, layoutContainer, [ARG0], _).

overlay_layout_maximum_layout_size(REF, ARG0, OUT) :- 
	object_call(REF, maximumLayoutSize, [ARG0], OUT).

overlay_layout_notify(REF) :- 
	object_call(REF, notify, [], _).

overlay_layout_add_layout_component(REF, ARG0, ARG1) :- 
	object_call(REF, addLayoutComponent, [ARG0, ARG1], _).

overlay_layout_add_layout_component(REF, ARG0, ARG1) :- 
	object_call(REF, addLayoutComponent, [ARG0, ARG1], _).

overlay_layout_remove_layout_component(REF, ARG0) :- 
	object_call(REF, removeLayoutComponent, [ARG0], _).

overlay_layout_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).

overlay_layout_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

overlay_layout_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

overlay_layout_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

overlay_layout_wait(REF) :- 
	object_call(REF, wait, [], _).

overlay_layout_invalidate_layout(REF, ARG0) :- 
	object_call(REF, invalidateLayout, [ARG0], _).

overlay_layout_get_layout_alignment_y(REF, ARG0, OUT) :- 
	object_call(REF, getLayoutAlignmentY, [ARG0], OUT).

overlay_layout_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

overlay_layout_get_layout_alignment_x(REF, ARG0, OUT) :- 
	object_call(REF, getLayoutAlignmentX, [ARG0], OUT).
