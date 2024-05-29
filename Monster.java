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
        try{
            return bfs.bfs(monster, rogue);
        }
        catch (RuntimeException ex){
            System.out.println("No path exists between Monster and Rogue.");
            return monster;
        }

    }
}
