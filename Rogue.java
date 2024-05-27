import java.util.LinkedList;
import java.util.Queue;

public class Rogue {
    private Game game;
    private Dungeon dungeon;
    private int N;

    public Rogue(Game game) {
        this.game    = game;
        this.dungeon = game.getDungeon();
        this.N       = dungeon.size();
    }


    // TAKE A RANDOM LEGAL MOVE
    // YOUR MAIN TASK IS TO RE-IMPLEMENT THIS METHOD TO DO SOMETHING INTELLIGENT
    public Site move() {
        /*Site monster = game.getMonsterSite();
        Site rogue   = game.getRogueSite();
        Site move    = null;

        // take random legal move
        int n = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Site site = new Site(i, j);
                if (dungeon.isLegalMove(rogue, site)) {
                    n++;
                    if (Math.random() <= 1.0 / n) move = site;
                }
            }
        }
        return move;*/
//        Site rogueSite = game.getRogueSite();
//        Site monsterSite = game.getMonsterSite();
//        Dungeon dungeon = game.getDungeon();
//
//        // 使用BFS找到离怪物最远的站点
//        Queue<Site> queue = new LinkedList<>();
//        queue.add(rogueSite);
//        boolean[][] visited = new boolean[dungeon.size()][dungeon.size()];
//        int[][] distance = new int[dungeon.size()][dungeon.size()];
//        visited[rogueSite.i()][rogueSite.j()] = true;
//
//        while (!queue.isEmpty()) {
//            Site current = queue.poll();
//
//            for (Site neighbor : dungeon.getNeighbors(current)) {
//                if (!visited[neighbor.i()][neighbor.j()] && dungeon.isLegalMove(current, neighbor)) {
//                    visited[neighbor.i()][neighbor.j()] = true;
//                    distance[neighbor.i()][neighbor.j()] = distance[current.i()][current.j()] + 1;
//                    queue.add(neighbor);
//                }
//            }
//        }
//
//        Site farthestSite = rogueSite;
//        int maxDistance = -1;
//
//        for (int i = 0; i < dungeon.size(); i++) {
//            for (int j = 0; j < dungeon.size(); j++) {
//                if (distance[i][j] > maxDistance) {
//                    maxDistance = distance[i][j];
//                    farthestSite = new Site(i, j);
//                }
//            }
//        }
//
//        return farthestSite;
        return game.getRogueSite();
    }

}
