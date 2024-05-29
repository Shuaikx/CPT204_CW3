import java.util.ArrayList;
import java.util.List;

public class DFS extends SearchTool{
    private List<Site> path;
    public DFS(Dungeon dungeon){
        super(dungeon, dungeon.size());
        path = new ArrayList<>();
    }

    public Site NextStep(Site monster, Site rogue) {
        return null;
    }

    public boolean dfs(Site current, Site target, boolean[][] visited) {
        if (current.equals(target)){
            return true;
        }
        if (visited[current.i()][current.j()]) return false;
        visited[current.i()][current.j()] = true;

        // 在走廊中的4个方向（N, E, S, W）
        int[][] directions = {
                {0, -1}, {1, 0}, {0, 1}, {-1, 0} // N, E, S, W
        };

        for (int[] dir : directions) {
            int newI = current.i() + dir[0];
            int newJ = current.j() + dir[1];
            Site neighbor = new Site(newI, newJ);

            if (isValidMove(current, neighbor, visited) && dungeon.isCorridor(neighbor)) {
                if (dfs(neighbor, target, visited)) {
                    path.add(neighbor);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isPathBetweenPoints(Site start, Site end) {
        boolean[][] visited = new boolean[N][N]; // 记录已访问的位置

        path = new ArrayList<>();
        path.add(start);
        return dfs(start, end, visited);
    }
    public List<Site> getPath(){
        return path;
    }

}
