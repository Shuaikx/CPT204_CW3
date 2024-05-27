public abstract class SearchTool {
    protected Dungeon dungeon;

    public SearchTool(Dungeon dungeon){

        this.dungeon = dungeon;
    }

    public abstract Site NextStep(Site monster, Site rogue);
}
