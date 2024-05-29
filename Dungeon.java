import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    private boolean[][] isRoom;        // is v-w a room site?
    private boolean[][] isCorridor;    // is v-w a corridor site?
    private List<Site> entrances;
    private int N;                     // dimension of dungeon
    private DFS dfs;
    private List<Circle> circles;

    public static  String CORRIDOR = "Corridor";
    public static  String ROOM = "Room";

    // initialize a new dungeon based on the given board
    public Dungeon(char[][] board) {
        N = board.length;
        isRoom     = new boolean[N][N];
        isCorridor = new boolean[N][N];
        entrances = new ArrayList<Site>();
        dfs = new DFS(this);
        circles = new ArrayList<>();
        entrances = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if      (board[i][j] == '.') isRoom[i][j] = true;
                else if (board[i][j] == '+') {
                    isCorridor[i][j] = true;
                    List<int[]> directions = new ArrayList<int[]>();
                    if(i>0) directions.add(new int[]{i-1,j});
                    if(i<N-1) directions.add(new int[]{i+1,j});
                    if(j>0) directions.add(new int[]{i,j-1});
                    if(j<N-1) directions.add(new int[]{i,j+1});
                    for(int[] direction: directions){
                        if(board[direction[0]][direction[1]] == '.'){
                            entrances.add(new Site(i,j));
                            break;
                        }
                    }
                }
            }
        }

        Site[][] pairs = getSitePairs(entrances);
        for (Site[] pair : pairs) {
            boolean isCircle = dfs.isPathBetweenPoints(pair[0], pair[1], Dungeon.CORRIDOR);
            if (isCircle) {
                Circle newCircle = new Circle(pair[0], pair[1], dfs.getPath());
                circles.add(newCircle);
            }
        }
        System.out.println("Circles Count: "+circles.size());
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
    public List<Site> getNeighborsInRoom(Site c) {
        List<Site> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] direction : directions) {
            int newI = c.i() + direction[0];
            int newJ = c.j() + direction[1];
            Site neighbor = new Site(newI, newJ);
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    public List<Site> getNeighborsInCorridor(Site c) {
        List<Site> neighbors = new ArrayList<>();
        int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        for (int[] direction : directions) {
            int newI = c.i() + direction[0];
            int newJ = c.j() + direction[1];
            Site neighbor = new Site(newI, newJ);
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    public List<Site> getEntrances(){ return entrances;}
    public DFS getDfs(){ return dfs;}
    public List<Circle> getCircles(){ return circles;}

    private Site[][] getSitePairs(List<Site> array) {
        List<Site[]> pairsList = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            for (int j = i + 1; j < array.size(); j++) {
                pairsList.add(new Site[]{array.get(i), array.get(j)});
            }
        }
        Site[][] pairsArray = new Site[pairsList.size()][2];
        for (int i = 0; i < pairsList.size(); i++) {
            pairsArray[i] = pairsList.get(i);
        }
        return pairsArray;
    }
}
