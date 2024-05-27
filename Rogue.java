import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Rogue extends Player {
    public Rogue(Game game) {
        super(game);
    }



    @Override
    public Site move() {
        Site monster = game.getMonsterSite();
        Site rogue = game.getRogueSite();
//        int maxDistance = -1;
//        Site remoteFormMonster = monster;
//        for(int i = 0; i<N; i++ ){
//            for(int j = 0; j<N; j++ ){
//                Site site = new Site(i,j);
//                if(dungeon.isCorridor(site) || dungeon.isRoom(site)){
//                    int distance = site.manhattanTo(monster);
//                    if(distance >= maxDistance){
//                        remoteFormMonster = site;
//                        maxDistance = distance;
//                    }
//                }
//            }
//        }
//        return bfs.NextStep(rogue, remoteFormMonster);
        List<Site> neighbors = dungeon.getNeighbors(rogue);
        int maxStep = -1;
        List<Site> maxSites = new ArrayList<Site>();
        neighbors.add(rogue); // also test rogue original site
        for(Site neighbor: neighbors){
            if(!dungeon.isLegalMove(rogue, neighbor)) continue; // Test weather it is legal
            bfs.NextStep(monster, neighbor);
            int neighborStep = bfs.GetStepCount();
            System.out.println(neighborStep);
            if(neighborStep > maxStep){
                maxSites.clear();
                maxSites.add(neighbor);
                maxStep = neighborStep;
            }
            else if(neighborStep == maxStep){
                maxSites.add(neighbor);
            }
        }

        // find the most remote point
        if(maxSites.size()>1){
            int maxDistance = -1;
            Site remoteFormMonster = monster;
            for(int i = 0; i<N; i++ ){
                for(int j = 0; j<N; j++ ){
                    Site site = new Site(i,j);
                    if(dungeon.isCorridor(site) || dungeon.isRoom(site)){
                        int distance = site.manhattanTo(monster);
                        if(distance >= maxDistance){
                            remoteFormMonster = site;
                            maxDistance = distance;
                        }
                    }
                }
            }
            if(maxSites.contains(remoteFormMonster)){
                return remoteFormMonster;
            }
        }

        return maxSites.getFirst();
    }
}
