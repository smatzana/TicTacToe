package ticTacToe;

import java.util.LinkedList;
import java.util.List;

import ticTacToe.Move.Position;

/**
 * Encapsulation of a TicTac Board
 *
 * @author sotiris
 */
public class Board {

    private final int dimension;
    private Player[][] board;
    private Position lastPosition;

    /**
     * Create an empty dimension x dimension board
     *
     * @param dimension
     */
    public Board(final int dimension) {
        this.dimension = dimension;
        this.lastPosition = null;
        this.board = new Player[dimension][dimension];
        for (int r = 0; r < this.dimension; r++) {
            for (int c = 0; c < this.dimension; c++) {
                this.board[r][c] = Player._;
            }
        }
    }

    /**
     * Create a board based on a String representation of a TicTac game
     * Used for testing purposes
     *
     * @param dimension
     * @param s String representation
     */
    public Board(final int dimension, String s) {
        this(dimension);
        if (s.length() != dimension*dimension) {
            throw new RuntimeException("Wrong string size");
        }
        for (int r = 0; r < this.dimension; r++) {
            for (int c = 0; c < this.dimension; c++) {
                this.board[r][c] = Player
                        .valueOf(
                                s.substring(
                                        r * this.dimension + c,
                                        r * this.dimension + c + 1));
            }
        }
    }

    /**
     * Create a new board based on an existing board, and with
     * Player p moving to a given position
     *
     * @param oldBoard
     * @param position, the position Player p moves to
     * @param p, Player
     */
    public Board(
            final Board oldBoard, Position position, Player p) {
        this.dimension = oldBoard.dimension;
        this.board = new Player[this.dimension][this.dimension];
        // Copy board
        for (int r = 0; r < this.dimension; r++) {
            for (int c = 0; c < this.dimension; c++) {
                this.board[r][c] = oldBoard.board[r][c];
            }
        }
        // Move player to position
        this.board[position.getCoordinates().fst][position.getCoordinates().snd] = p;
        this.lastPosition = position;
    }

    /**
     * Check if board has a solution for Player p
     *
     * @param p Player to check for
     * @return boolean
     */
    public boolean hasSolution(Player p) {
        boolean retVal = false;
        do {
            if (this.check(p, true)) {
                retVal = true;
                break;
            }
            if (this.check(p, false)) {
                retVal = true;
                break;
            }
            if (this.checkDiagonal(p)) {
                retVal = true;
            }
        } while(false);
        return retVal;
    }

    /**
     * Check horizontally or vertically for a solution
     * for Player P
     *
     * @param p
     * @param horizontal
     * @return boolean
     */
    private boolean check(Player p, boolean horizontal) {
        boolean hasSolution = true;
        for (int r = 0; r < this.dimension; r++) {
            for (int c = 0; c < this.dimension; c++) {
                hasSolution = true;
                Player check = horizontal? this.board[r][c]: this.board[c][r];
                if (!check.equals(p)) {
                    hasSolution = false;
                    break;
                }
            }
            if (hasSolution) {
                break;
            }
        }
        return hasSolution;
    }

    /**
     * Check for a solution diagonally for Player P
     *
     * @param p
     * @return boolean
     */
    private boolean checkDiagonal(Player p) {
        boolean firstCheck = true;
        for (int r = 0; r < this.dimension; r++) {
            if ( this.board[r][r] != p) {
                firstCheck = false;
            }
        }
        if (!firstCheck) {
            for (int r = this.dimension -1; r >= 0 ; r--) {
                if ( this.board[this.dimension-r-1][r] != p) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get the list of empty slots for a board
     *
     * @return  List\<Position\>
     */
    public List<Position> getEmptySlots(){
        List<Position> retList = new LinkedList<Position>();

        for (int r = 0; r < this.dimension; r++) {
            for (int c = 0; c < this.dimension; c++) {
               if (this.board[r][c] == Player._) {
                   retList.add(new Position(r,c));
               }
            }
        }
        return retList;
    }

    /**
     * Rotate the board by 90, 180, 270 degrees and
     * get the string representation of each rotation
     * Used for caching of previously computed results
     *
     * @return
     */
    public List<String> getRotationSignatures() {
        List<String> retList = new LinkedList<String>();
        retList.add(this.getSignature(this.board));
        Player[][] rotate90 = this.rotate(this.board);
        retList.add(this.getSignature(rotate90));
        Player[][] rotate180 = this.rotate(rotate90);
        retList.add(this.getSignature(rotate180));
        Player[][] rotate270 = this.rotate(rotate180);
        retList.add(this.getSignature(rotate270));
        return retList;
    }

    /**
     * Get the string representation for this board
     * @return
     */
    public String getSignature() {
        return this.getSignature(this.board);
    }

    /**
     * Get the string representation for a matrix
     *
     * @param m
     * @return
     */
    private String getSignature(Player[][] m) {
        StringBuffer signature = new StringBuffer();
        for( Player[] arr : m) {
            for(Player p : arr) {
                signature.append(p.toString());
            }
        }
        return signature.toString();
    }

    /**
     * Rotate a matrix by 90 degrees
     *
     * @param m
     * @return  matrix
     */
    private Player[][] rotate(Player[][] m) {
        Player[][] rotateM = new Player[m[0].length][m.length];
        for (int i= 0; i< m.length; i++){
            for (int j= 0; j< m[0].length; j++){
                rotateM[i][j] = m[j][m.length-i-1];
            }
        }
        return rotateM;
    }

    public Position getLastPosition() {
        return this.lastPosition;
    }
}
