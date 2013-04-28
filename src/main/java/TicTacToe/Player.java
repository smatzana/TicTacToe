package TicTacToe;

/**
 * A TicTacToe board player: one of X, O or empty (_)
 *
 * @author sotiris
 */
public enum Player {

    X,
    O,
    _;

    public Player getOpponent(){
        switch(this) {
            case X: return O;
            case O: return X;
            default: return _;
        }
    }

}
