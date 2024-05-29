public abstract class SearchTool {
    protected Dungeon dungeon;
    protected int N;

    public SearchTool(Dungeon dungeon, int N){
        this.dungeon = dungeon;
        this.N = N;
    }

    public abstract Site NextStep(Site monster, Site rogue);

    protected boolean isValidMove(Site current, Site neighbor, boolean[][] visited) {
        int i = neighbor.i(); // 获取邻居位置的行索引
        int j = neighbor.j(); // 获取邻居位置的列索引
        if (i < 0 || j < 0 || i >= N || j >= N) return false; // 检查是否越界
        if (visited[i][j]) return false; // 检查是否已访问
        return dungeon.isLegalMove(current, neighbor); // 检查移动是否合法
    }
}


