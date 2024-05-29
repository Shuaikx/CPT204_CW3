import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DFS extends SearchTool{
    private List<Site> path;

    public DFS(Dungeon dungeon){
        super(dungeon, dungeon.size());
        path = new ArrayList<>();
    }

    public boolean dfs(Site current, Site target, boolean[][] visited, String pathType) {
        if (current.equals(target)) return true;
        if (visited[current.i()][current.j()]) return false;
        visited[current.i()][current.j()] = true;


        List<Site> neighbors;
        if(pathType.equals(Dungeon.ROOM)){
            neighbors = dungeon.getNeighborsInRoom(current);
        }
        else{
            neighbors = dungeon.getNeighborsInCorridor(current);
        }

        for (Site neighbor : neighbors) {

            if (isValidMove(current, neighbor, visited) &&
                    ((dungeon.isCorridor(neighbor)&&pathType.equals(Dungeon.CORRIDOR)) || (dungeon.isRoom(neighbor)&&pathType.equals(Dungeon.ROOM))))
            {
                if (dfs(neighbor, target, visited, pathType)) {
                    path.add(neighbor);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isPathBetweenPoints(Site start, Site end, String pathType) {
        boolean[][] visited = new boolean[N][N]; // 记录已访问的位置

        path = new ArrayList<>();
        path.add(start);
        return dfs(start, end, visited, pathType);
    }
    public List<Site> getPath(){
        return path;
    }

}
