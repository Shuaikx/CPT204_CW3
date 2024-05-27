import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    private boolean[][] isRoom;        // is v-w a room site?
    private boolean[][] isCorridor;    // is v-w a corridor site?
    private int N;                     // dimension of dungeon

    // initialize a new dungeon based on the given board
    public Dungeon(char[][] board) {
        N = board.length;
        isRoom     = new boolean[N][N];
        isCorridor = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if      (board[i][j] == '.') isRoom[i][j] = true;
                else if (board[i][j] == '+') isCorridor[i][j] = true;
            }
        }
    }

    // return dimension of dungeon
    public int size() { return N; }

    // does v correspond to a corridor site? 
    public boolean isCorridor(Site v) {
        int i = v.i();
        int j = v.j();
        if (i < 0 || j < 0 || i >= N || j >= N) return false;
        return isCorridor[i][j];
    }

    // does v correspond to a room site?
    public boolean isRoom(Site v) {
        int i = v.i();
        int j = v.j();
        if (i < 0 || j < 0 || i >= N || j >= N) return false;
        return isRoom[i][j];
    }

    // does v correspond to a wall site?
    public boolean isWall(Site v) {
        return (!isRoom(v) && !isCorridor(v));
    }

    // does v-w correspond to a legal move?
    public boolean isLegalMove(Site v, Site w) {
        int i1 = v.i();
        int j1 = v.j();
        int i2 = w.i();
        int j2 = w.j();
        if (i1 < 0 || j1 < 0 || i1 >= N || j1 >= N) return false;
        if (i2 < 0 || j2 < 0 || i2 >= N || j2 >= N) return false;
        if (isWall(v) || isWall(w)) return false;
        if (Math.abs(i1 - i2) > 1)  return false;
        if (Math.abs(j1 - j2) > 1)  return false;
        if (isRoom(v) && isRoom(w)) return true;
        if (i1 == i2)               return true;
        if (j1 == j2)               return true;

        return false;
    }

    // add new function
    public List<Site> getNeighbors(Site c) {
        List<Site> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] direction : directions) {
            int newI = c.i() + direction[0];
            int newJ = c.j() + direction[1];
            Site neighbor = new Site(newI, newJ);
            neighbors.add(neighbor);
//            if (isLegalMove(c, neighbor)) {
//                neighbors.add(neighbor);
//            }
//            else{
//                neighbors.add(new Site(-1,-1));
//            }
        }
        return neighbors;
    }

}
