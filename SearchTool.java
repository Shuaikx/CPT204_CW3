public abstract class SearchTool {
    protected Dungeon dungeon;
    protected int N;

    public SearchTool(Dungeon dungeon, int N){
        this.dungeon = dungeon;
        this.N = N;
    }

    protected boolean isValidMove(Site current, Site target, boolean[][] visited) {
        int i = target.i(); // 获取邻居位置的行索引
        int j = target.j(); // 获取邻居位置的列索引
        if (i < 0 || j < 0 || i >= N || j >= N) return false; // 检查是否越界
        if (visited[i][j]) return false; // 检查是否已访问
        return dungeon.isLegalMove(current, target); // 检查移动是否合法
    }
}


