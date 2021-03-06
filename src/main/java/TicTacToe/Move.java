package ticTacToe;

import org.apache.commons.lang3.tuple.Pair;

/**
 * A TicTacToe Move
 *
 * Encapsulates an Outcome (win, tie, lose) of a possible board move
 * along with its coordinates on the board
 *
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

    /**
     * Create a move out of a board position, an outcome and a
     * # of moves
     *
     * @param position
     * @param outcome
     */
    public Move(Position position, Outcome outcome) {
        this.outcome = outcome;
        this.position = position;
    }

    /**
     * Create a new Move that has the reverse outcome of this
     *
     * @return Move
     */
    public Move reverse() {
        Move retVal = new Move(new Position(this.getPosition()),
                this.getOutcome().reverse());
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

    public String toString() {
        return  this.getPosition().toString() + ", result:" + this.getOutcome();
    }

    /**
     * Encapsulate the coordinates of a board position
     *
     */
    protected static class Position {
        private Pair<Integer,Integer> coordinates;

        public Position(Integer row, Integer column) {
            this.coordinates = Pair.of(row, column);
        }

        public Position(Position coordinates) {
            this.coordinates = Pair.of(
                    coordinates.coordinates.getLeft(),
                    coordinates.coordinates.getRight());
        }

        protected Pair<Integer, Integer> getCoordinates() {
            return this.coordinates;
        }

        protected void setCoordinates(Pair<Integer, Integer> coordinates) {
            this.coordinates = coordinates;
        }

        public String toString() {
            return "[" + this.coordinates
                    .getLeft().toString() + " " + this.coordinates.getRight().toString() + "]";
        }
    }
}
