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

import static org.junit.Assert.assertEquals;

/**
 * @author sotirios
 */
public class TicTacToeTest {

    @Test
    public void emptyBoardTest(){
        Board board = new Board(3,
                "___"+
                "___" +
                "___"
        );
        Move answer = TicTacToe.findBestMove(board, Player.X);
        // Should pick a corner (0,0)
        assertEquals(answer.getPosition().getCoordinates().getLeft(),
                Integer.valueOf(0));
        assertEquals(answer.getPosition().getCoordinates().getRight(),
                Integer.valueOf(0));
    }

    @Test
    public void shouldBlockOpponentXTest(){
        Board board = new Board(3,
                "X__"+
                "OX_" +
                "O__"
        );
        Move answer = TicTacToe.findBestMove(board, Player.O);
        // Should pick corner (2,2)
        assertEquals(answer.getPosition().getCoordinates().getLeft(),
                Integer.valueOf(2));
        assertEquals(answer.getPosition().getCoordinates().getRight(),
                Integer.valueOf(2));
    }

    @Test
    public void shouldBlockOpponentOTest(){
        Board board = new Board(3,
                    "X__"+
                    "OX_" +
                    "O_O"
        );
        Move answer = TicTacToe.findBestMove(board, Player.X);
        // Should pick  (2,1)
        assertEquals(answer.getPosition().getCoordinates().getLeft(),
                Integer.valueOf(2));
        assertEquals(answer.getPosition().getCoordinates().getRight(),
                Integer.valueOf(1));
    }

    @Test
    public void shouldPickCornerTest(){
        Board board = new Board(3,
                    "___"+
                    "_O_" +
                    "___"
        );
        Move answer = TicTacToe.findBestMove(board, Player.X);
        // Should pick corner (0,0)
        assertEquals(answer.getPosition().getCoordinates().getLeft(),
                Integer.valueOf(0));
        assertEquals(answer.getPosition().getCoordinates().getRight(),
                Integer.valueOf(0));
    }

    @Test
    public void shouldPickCenterTest(){
        Board board = new Board(3,
                    "X__"+
                    "___" +
                    "___"
        );
        Move answer = TicTacToe.findBestMove(board, Player.O);
        // Should pick center (1,1)
        assertEquals(answer.getPosition().getCoordinates().getLeft(),
                Integer.valueOf(1));
        assertEquals(answer.getPosition().getCoordinates().getRight(),
                Integer.valueOf(1));
    }
}
