public abstract class SearchTool {
    protected Dungeon dungeon;
    protected int N;

    public SearchTool(Dungeon dungeon, int N){
        this.dungeon = dungeon;
        this.N = N;
    }

    public abstract Site NextStep(Site monster, Site rogue);
}
