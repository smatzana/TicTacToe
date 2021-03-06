package ticTacToe;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import ticTacToe.Move.Outcome;
import ticTacToe.Move.Position;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Find the best move for a player and a TicTacToe Board
 *
 * @author sotiris
 */
class TicTacToe {

    /*
        Cache: a board solution is the same for its 3 other rotations:
        90, 180 and 270 degrees.
        Once we solve a board, we record the same solution for
        that player and its 3 rotations
     */
    private static Map<Pair<Player,String>, Move> cache =
            new HashMap<Pair<Player,String>, Move>();

    /**
     * Find next best move for a Player and a Board
     *
     * @param board
     * @param player
     * @return  best Move
     */
    public static Move findBestMove(Board board, Player player) {

        Pair<Player,String> cachePair = Pair.of(player, board.getSignature());

        /* if we have the solution in the cache, just get it from there */
        if ( cache.containsKey(cachePair)){
            return cache.get(cachePair);
        }

        /*
            Recursion terminates if there's a solution for
            this player or his opponent
         */
        if (board.hasSolution(player)) {
            return new Move(board.getLastPosition(),
                    Outcome.WIN);
        }
        if (board.hasSolution(player.getOpponent())) {
            return new Move(board.getLastPosition(),
                    Outcome.LOSE);
        }

        List<Move> resList = new LinkedList<Move>();
        Move bestMove = null;

        /*
            Start placing player in the empty slots of the current
            board, and select the best outcome out of those moves
         */
        for(Position emptySlot : board.getEmptySlots()) {
            // Place player in open slot
            Board newBoard = new Board(board, emptySlot, player);
            // See what the opponent's best move is
            Move nextMove = findBestMove(newBoard, player.getOpponent());
            if (nextMove == null) {
                // TIE
                nextMove = new Move(emptySlot, Outcome.TIE);
            }
            /* Player's best next move is the opposite of the opponent's
               so if the opponent reports a WIN, that's a LOSE for this
               player etc.
             */
            nextMove = nextMove.reverse();
            nextMove.setPosition(emptySlot);
            resList.add(nextMove);

            // Do we have a better solution than the previous one?
            if( nextMove.compareTo(bestMove) > 0 ) {
                bestMove = nextMove;
            }
        }

        /* We have selected the best solution, record it in the cache
            for this board and its 3 rotation counterparts
         */
        for (String s : board.getRotationSignatures()) {
            Pair<Player,String> cacheKey = Pair.of(player, s);
            cache.put(cacheKey, bestMove);
        }

        return bestMove;
    }

    public static void main(String... args) {

        Logger LOGGER = Logger.getLogger(TicTacToe.class.getName());

        Board board = new Board(3,
                "___"+
                "___" +
                "___"
        );
        Move answer = TicTacToe.findBestMove(board, Player.X);
        Board newBoard = new Board(board, answer.getPosition(), Player.X);
        LOGGER.info("Board:" + board.toString()
                + "Best move is:" + answer + "\n"
                + "New Board:" + newBoard);
    }
}
