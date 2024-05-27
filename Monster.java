import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Monster extends Player{
    public Monster(Game game) {
        super(game);
    }

    @Override
    public Site move() {
        Site monster = game.getMonsterSite();
        Site rogue = game.getRogueSite();
        return bfs.NextStep(monster, rogue);
    }
}
