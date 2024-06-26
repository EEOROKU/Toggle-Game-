package ToggleGame.backend;
import ToggleGame.frontend.ToggleGameInteraction;

import java.util.*;
/**
 AI Assistance was used in the following methods in this file:

 - TOGGLE_RULES()
 - buttonClicked()
 - movesToSolve()

 **/
/**
 * Buttons have the following order / placement on the screen
 *
 *  0 1 2
 *  3 4 5
 *  6 7 8
 *
 * For the Colors: BLACK is 0 and WHITE = 1
 *
 * This file is incomplete and should be completed
 * Once it is completed please update this message
 */
public class  ToggleGameEngine implements ToggleGameInteraction {
    private static final int[][] TOGGLE_RULES = {
            {0, 1, 3},
            {0, 1, 2, 4},
            {1, 2, 5},
            {0, 3, 4, 6},
            {1, 3, 4, 5, 7},
            {2, 4, 5, 8},
            {3, 6, 7},
            {4, 6, 7, 8},
            {5, 7, 8}
    };
    /**
     * Initialize and return the game board for a ToggleGame (9x "1")
     * @return the String "111111111" to start a game with all white squares
     */
    @Override
    public String initializeGame() {
        //starter code ... replace the below code with a string containing all 1's
        String ret ="111111111";
        return ret;
    }

    /**
     * Update the game board for the given button that was clicked.
     * Squares marked as 0 are black and 1 is white
     *
     * @param button the game board square button that was clicked (between 0 and 8)
     *
     * @return the updated game board as a String giving the button colors in order
     *         with "0" for black and "1" for white.
     *
     * @throws IllegalArgumentException when button is outside 0-8
     */

    @Override
    public String buttonClicked(String current, int button) {
        //starter code...replace the below code
        char[] board = current.toCharArray();
        for (int i : TOGGLE_RULES[button]) {
            board[i] = board[i] == '0' ? '1' : '0';
        }
        return new String(board);
    }

    /**
     * Return a sequence of moves that leads in the minimum number of moves
     * from the current board state to the target state
     *
     * @param current the current board state given as a String of 1's (white square)
     *                and 0's (black square)
     * @param target the target board state given as a String of 1's (white square)
     *               and 0's (black square)
     * @return the sequence of moves to advance the board from current to target.
     *         Each move is the number associated with a button on the board. If no moves are
     *         required to advance the currentBoard to the target an empty array is returned.
     */
    @Override
    public int[] movesToSolve(String current, String target) {
        //starter code ... replace the below
        if (current.equals(target)) {
            return new int[0];
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, List<Integer>> visited = new HashMap<>();

        queue.add(current);
        visited.put(current, new ArrayList<>());

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            List<Integer> curMoves = visited.get(cur);

            for (int i = 0; i < 9; i++) {
                String nextState = buttonClicked(cur, i);
                if (visited.containsKey(nextState)) {
                    continue;
                }
                List<Integer> nextMoves = new ArrayList<>(curMoves);
                nextMoves.add(i);

                if (nextState.equals(target)) {
                    return nextMoves.stream().mapToInt(Integer::intValue).toArray();
                }

                visited.put(nextState, nextMoves);
                queue.add(nextState);
            }
        }

        return new int[0];
    }

    /**
     * Return the minimum required number of required moves (button clicks)
     * to advance the current board to the target board.
     *
     * @param current the current board state given as a String of 1's (white square)
     *                and 0's (black square)
     * @param target the target board state given as a String of 1's (white square)
     *               and 0's (black square)
     * @return the minimum number of moves to advance the current board
     * to the target
     */
    @Override
    public int minNumberOfMoves(String current, String target) {
        //starter code ... replace the below
        if (current.equals(target)) {
            return 0;
        }
        return movesToSolve(current, target).length;
    }
}
