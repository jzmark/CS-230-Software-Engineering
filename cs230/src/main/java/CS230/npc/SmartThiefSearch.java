package CS230.npc;

import CS230.map.Map;
import CS230.map.Tile;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class for conducting BFS to find a path for the smart thief
 * @author Caleb Ocansey
 * @version 1.0
 */
public class SmartThiefSearch {

    private static final int MAX_STEPS = 100;

    private static Queue<int[]> path = new LinkedList<>();
    //used to iterate through all the directions of grid
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * Method that initiates a BFS and returns boolean if it's the goal or not
     * @param tiles
     * @param startRow  the row of the SmartThief
     * @param startCol  the column of the SmartThief
     * @param goalRow the row of the nearest item
     * @param goalCol the column of the nearest item
     * @return boolean true if there is a path to the goal
     */
    public static boolean bfs(Map x, Tile[][] tiles, int startRow,
                              int startCol, int goalRow, int goalCol ) {
        int rows = x.getMAP_MAX_X();
        int cols = x.getMAP_MAX_Y();

        int steps=0;

        boolean[][] visited = new boolean[rows][cols];

        // create a queue for BFS
        Queue<int[]> queue = new LinkedList<>();


        // mark the start cell as visited and enqueue it
        visited[startRow][startCol] = true;
        queue.add(new int[]{startRow, startCol});

        while (!queue.isEmpty() && steps <= MAX_STEPS) {
            steps++;

            // dequeue the current cell
            int[] curr = queue.poll();
            int currRow = curr[0];
            int currCol = curr[1];

            // add the current cell to the path
            path.add(curr);

            // check if we have reached the goal cell
            if (currRow == goalRow && currCol == goalCol) {
                return true;
            }


            // iterate through the four possible directions
            for (int[] dir : DIRS) {
                int nextRow = currRow + dir[0];
                int nextCol = currCol + dir[1];

                int rowDiff = currRow - nextRow;
                int colDiff = currCol - nextCol;
                int dist = rowDiff + colDiff;
                
                
                // check if the next cell is valid, not visited, and has
                // at least one common color with the current cell
                if (((nextRow >= 0 && nextRow < rows ) &&
                        (nextCol >= 0 && nextCol < cols) &&
                        !visited[nextRow][nextCol] &&
                        
                        tiles[currRow][currCol].isLegalJump(tiles[nextRow][nextCol])
                        
                )&& (dist <= 1)) {
                    // mark the cell as visited and enqueue it
                    
                    visited[currRow][currCol] = true;
                    
                    queue.add(new int[]{nextRow, nextCol, currRow, currCol});


                }

            }


        }


        // if reached here, no path to the goal
        return false;
    }


    /**
     * method for getting the queue
     */
    public static Queue<int[]> getQueue(){
        return path;
    }
}