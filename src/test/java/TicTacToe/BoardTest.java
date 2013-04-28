/*
 * Telefónica Digital - Product Development and Innovation
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Copyright (c) Telefónica Investigación y Desarrollo S.A.U.
 * All rights reserved.
 */

package ticTacToe;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author sotirios
 */
public class BoardTest {

    @Test
    public void hasSolutionTest() {
        Board board = new Board(3,
                "XOX"+
                "OX_"+
                "O_X");
        assertTrue(board.hasSolution(Player.X));
        assertFalse(board.hasSolution(Player.O));
    }

    @Test
    public void hasHorizontalSolutionTest() {
        Board board = new Board(3,
                "XXX"+
                "OO_"+
                "O_X");
        assertTrue(board.hasSolution(Player.X));
        assertFalse(board.hasSolution(Player.O));
    }

    @Test
    public void hasVerticalSolutionTest() {
        Board board = new Board(3,
                "_OX"+
                "OO_"+
                "OOX");
        assertTrue(board.hasSolution(Player.O));
        assertFalse(board.hasSolution(Player.X));
    }

    @Test
    public void doesNotHaveSolutionTest() {
        Board board = new Board(3,
                "XOX"+
                "OX_"+
                "O_O");
        assertFalse(board.hasSolution(Player.X));
        assertFalse(board.hasSolution(Player.O));
    }

}
