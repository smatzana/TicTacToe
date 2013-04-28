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

package TicTacToe;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author sotirios
 */
public class PlayerTest {

    @Test
    public void getOpponentTest() {
        assertTrue(Player.X.getOpponent().equals(Player.O));
        assertTrue(Player.O.getOpponent().equals(Player.X));
    }
}
