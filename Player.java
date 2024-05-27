public abstract class Player {
    protected Game game;
    protected Dungeon dungeon;
    protected int N;

    protected BFS bfs;
    protected DFS dfs;

    public Player(Game game) {
        this.game    = game;
        this.dungeon = game.getDungeon();
        this.N       = dungeon.size();
        this.bfs     = new BFS(dungeon);
        this.dfs     = new DFS(dungeon);
    }

    public abstract Site move();
}
