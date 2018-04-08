package com.ubs.intrvw;

import org.junit.Test;

import static com.ubs.intrvw.CellStatus.DARK;
import static com.ubs.intrvw.CellStatus.EMPTY;
import static com.ubs.intrvw.CellStatus.LIGHT;
import static org.junit.Assert.*;

public class CellStatusTest {

    @Test
    public void testSameColor(){
        assertTrue(DARK.isSameColor(DARK));
        assertFalse(DARK.isSameColor(LIGHT));
        assertFalse(DARK.isSameColor(EMPTY));
    }

    @Test
    public void testOppositeColor(){
        assertTrue(DARK.isOppositeColor(LIGHT));
        assertTrue(LIGHT.isOppositeColor(DARK));
        assertFalse(DARK.isOppositeColor(DARK));
        assertFalse(LIGHT.isOppositeColor(LIGHT));
        assertFalse(DARK.isOppositeColor(EMPTY));
        assertFalse(LIGHT.isOppositeColor(EMPTY));
    }

}