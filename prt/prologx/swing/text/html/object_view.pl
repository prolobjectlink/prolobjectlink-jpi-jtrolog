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

object_view_BADBREAKWEIGHT(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', badbreakweight, OUT).

object_view_GOODBREAKWEIGHT(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', goodbreakweight, OUT).

object_view_EXCELLENTBREAKWEIGHT(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', excellentbreakweight, OUT).

object_view_FORCEDBREAKWEIGHT(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', forcedbreakweight, OUT).

object_view_X_AXIS(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', x_axis, OUT).

object_view_Y_AXIS(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', y_axis, OUT).

object_view_CENTER(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', center, OUT).

object_view_TOP(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', top, OUT).

object_view_LEFT(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', left, OUT).

object_view_BOTTOM(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', bottom, OUT).

object_view_RIGHT(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', right, OUT).

object_view_NORTH(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', north, OUT).

object_view_NORTH_EAST(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', north_east, OUT).

object_view_EAST(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', east, OUT).

object_view_SOUTH_EAST(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', south_east, OUT).

object_view_SOUTH(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', south, OUT).

object_view_SOUTH_WEST(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', south_west, OUT).

object_view_WEST(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', west, OUT).

object_view_NORTH_WEST(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', north_west, OUT).

object_view_HORIZONTAL(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', horizontal, OUT).

object_view_VERTICAL(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', vertical, OUT).

object_view_LEADING(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', leading, OUT).

object_view_TRAILING(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', trailing, OUT).

object_view_NEXT(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', next, OUT).

object_view_PREVIOUS(OUT) :- 
	object_get('javax.swing.text.html.ObjectView', previous, OUT).

object_view(ARG0, OUT) :- 
	object_new('javax.swing.text.html.ObjectView', [ARG0], OUT).

object_view_preference_changed(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, preferenceChanged, [ARG0, ARG1, ARG2], _).

object_view_to_string(REF, OUT) :- 
	object_call(REF, toString, [], OUT).

object_view_get_parent(REF, OUT) :- 
	object_call(REF, getParent, [], OUT).

object_view_get_element(REF, OUT) :- 
	object_call(REF, getElement, [], OUT).

object_view_get_container(REF, OUT) :- 
	object_call(REF, getContainer, [], OUT).

object_view_get_child_allocation(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, getChildAllocation, [ARG0, ARG1], OUT).

object_view_notify_all(REF) :- 
	object_call(REF, notifyAll, [], _).

object_view_equals(REF, ARG0, OUT) :- 
	object_call(REF, equals, [ARG0], OUT).

object_view_get_minimum_span(REF, ARG0, OUT) :- 
	object_call(REF, getMinimumSpan, [ARG0], OUT).

object_view_get_break_weight(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, getBreakWeight, [ARG0, ARG1, ARG2], OUT).

object_view_get_view_index(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, getViewIndex, [ARG0, ARG1], OUT).

object_view_get_end_offset(REF, OUT) :- 
	object_call(REF, getEndOffset, [], OUT).

object_view_get_view_index(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, getViewIndex, [ARG0, ARG1, ARG2], OUT).

object_view_remove(REF, ARG0) :- 
	object_call(REF, remove, [ARG0], _).

object_view_changed_update(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, changedUpdate, [ARG0, ARG1, ARG2], _).

object_view_insert_update(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, insertUpdate, [ARG0, ARG1, ARG2], _).

object_view_get_alignment(REF, ARG0, OUT) :- 
	object_call(REF, getAlignment, [ARG0], OUT).

object_view_append(REF, ARG0) :- 
	object_call(REF, append, [ARG0], _).

object_view_get_preferred_span(REF, ARG0, OUT) :- 
	object_call(REF, getPreferredSpan, [ARG0], OUT).

object_view_get_next_visual_position_from(REF, ARG0, ARG1, ARG2, ARG3, ARG4, OUT) :- 
	object_call(REF, getNextVisualPositionFrom, [ARG0, ARG1, ARG2, ARG3, ARG4], OUT).

object_view_get_tool_tip_text(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, getToolTipText, [ARG0, ARG1, ARG2], OUT).

object_view_get_class(REF, OUT) :- 
	object_call(REF, getClass, [], OUT).

object_view_notify(REF) :- 
	object_call(REF, notify, [], _).

object_view_get_attributes(REF, OUT) :- 
	object_call(REF, getAttributes, [], OUT).

object_view_break_view(REF, ARG0, ARG1, ARG2, ARG3, OUT) :- 
	object_call(REF, breakView, [ARG0, ARG1, ARG2, ARG3], OUT).

object_view_set_size(REF, ARG0, ARG1) :- 
	object_call(REF, setSize, [ARG0, ARG1], _).

object_view_model_to_view(REF, ARG0, ARG1, ARG2, ARG3, ARG4, OUT) :- 
	object_call(REF, modelToView, [ARG0, ARG1, ARG2, ARG3, ARG4], OUT).

object_view_model_to_view(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, modelToView, [ARG0, ARG1, ARG2], OUT).

object_view_model_to_view(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, modelToView, [ARG0, ARG1], OUT).

object_view_get_document(REF, OUT) :- 
	object_call(REF, getDocument, [], OUT).

object_view_view_to_model(REF, ARG0, ARG1, ARG2, ARG3, OUT) :- 
	object_call(REF, viewToModel, [ARG0, ARG1, ARG2, ARG3], OUT).

object_view_view_to_model(REF, ARG0, ARG1, ARG2, OUT) :- 
	object_call(REF, viewToModel, [ARG0, ARG1, ARG2], OUT).

object_view_get_start_offset(REF, OUT) :- 
	object_call(REF, getStartOffset, [], OUT).

object_view_get_maximum_span(REF, ARG0, OUT) :- 
	object_call(REF, getMaximumSpan, [ARG0], OUT).

object_view_get_resize_weight(REF, ARG0, OUT) :- 
	object_call(REF, getResizeWeight, [ARG0], OUT).

object_view_set_parent(REF, ARG0) :- 
	object_call(REF, setParent, [ARG0], _).

object_view_hash_code(REF, OUT) :- 
	object_call(REF, hashCode, [], OUT).

object_view_get_graphics(REF, OUT) :- 
	object_call(REF, getGraphics, [], OUT).

object_view_remove_all(REF) :- 
	object_call(REF, removeAll, [], _).

object_view_get_view_count(REF, OUT) :- 
	object_call(REF, getViewCount, [], OUT).

object_view_create_fragment(REF, ARG0, ARG1, OUT) :- 
	object_call(REF, createFragment, [ARG0, ARG1], OUT).

object_view_remove_update(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, removeUpdate, [ARG0, ARG1, ARG2], _).

object_view_replace(REF, ARG0, ARG1, ARG2) :- 
	object_call(REF, replace, [ARG0, ARG1, ARG2], _).

object_view_is_visible(REF, OUT) :- 
	object_call(REF, isVisible, [], OUT).

object_view_insert(REF, ARG0, ARG1) :- 
	object_call(REF, insert, [ARG0, ARG1], _).

object_view_get_component(REF, OUT) :- 
	object_call(REF, getComponent, [], OUT).

object_view_get_view_factory(REF, OUT) :- 
	object_call(REF, getViewFactory, [], OUT).

object_view_get_view(REF, ARG0, OUT) :- 
	object_call(REF, getView, [ARG0], OUT).

object_view_wait(REF, ARG0) :- 
	object_call(REF, wait, [ARG0], _).

object_view_wait(REF) :- 
	object_call(REF, wait, [], _).

object_view_paint(REF, ARG0, ARG1) :- 
	object_call(REF, paint, [ARG0, ARG1], _).

object_view_wait(REF, ARG0, ARG1) :- 
	object_call(REF, wait, [ARG0, ARG1], _).
