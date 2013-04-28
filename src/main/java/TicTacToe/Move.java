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

import com.sun.tools.javac.util.Pair;

/**
 * A TicTacToe Move
 *
 * Encapsulates an Outcome (win, tie, lose) of a possible board move
 * along with its coordinates on the board, and the # of plays from
 * the beginning of the game
 * @author sotiris
 */
public class Move implements Comparable<Move> {

    enum Outcome implements Comparable<Outcome> {
        LOSE,
        TIE,
        WIN;

        public Outcome reverse() {
            switch(this) {
                case WIN: return LOSE;
                case LOSE: return WIN;
                default: return TIE;
            }
        }
    }

    private Outcome outcome;
    private Position position;
    private Integer moves;

    /**
     * Create a move out of a board position, an outcome and a
     * # of moves
     *
     * @param position
     * @param outcome
     * @param moves
     */
    public Move(Position position, Outcome outcome, int moves) {
        this.setOutcome(outcome);
        this.setPosition(position);
        this.setMoves(moves);
    }

    /**
     * Create a new Move that has the reverse outcome of this
     *
     * @return Move
     */
    public Move reverse() {
        Move retVal = new Move(new Position(this.getPosition()),
                this.getOutcome().reverse(), this.getMoves());
        return retVal;
    }

    /**
     * Compare two moves
     *
     * Return the result of simply comparing
     * the outcomes of the moves: Win > Tie > Lose
     *
     * @param move
     * @return int
     */
    @Override
    public int compareTo(Move move) {
        if ( move == null) {
            return 1;
        }
        return this.getOutcome().compareTo(move.getOutcome());
    }

    public Outcome getOutcome() {
        return this.outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    private Integer getMoves() {
        return this.moves;
    }

    private void setMoves(int moves) {
        this.moves = moves;
    }

    public String toString() {
        return  this.getPosition().toString() + ", result:" + this.getOutcome()
                + " in " + this.getMoves() + " moves";
    }

    /**
     * Encapsulate the coordinates of a board position
     *
     */
    protected static class Position {
        private Pair<Integer,Integer> coordinates;

        public Position(Integer row, Integer column) {
            this.setCoordinates(new Pair<Integer, Integer>(row, column));
        }

        public Position(Position coordinates) {
            this.setCoordinates(
                    Pair.of(
                            coordinates.coordinates.fst,
                            coordinates.coordinates.snd));
        }

        protected Pair<Integer, Integer> getCoordinates() {
            return this.coordinates;
        }

        protected void setCoordinates(Pair<Integer, Integer> coordinates) {
            this.coordinates = coordinates;
        }

        public String toString() {
            return "[" + this.coordinates
                    .fst.toString() + " " + this.coordinates.snd.toString() + "]";
        }
    }
}
