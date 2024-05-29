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
        List<Circle> circles = dungeon.getCircles();
        if(circles.isEmpty()){
            return leaveMonster(monster, rogue);
        }
        else{
            return approachCircle(monster, rogue, circles);
        }
    }

    private Site approachCircle(Site monster, Site rogue, List<Circle> circles){


        List<Site> cannotStep = getStepsCloseMonster(monster, rogue);
        int minStep = 2*N;

        for(Circle circle: circles){
            if(!circle.isInCorridor(rogue)){
                Site site = bfs.bfs(rogue, circle.getEntrance1());
                if(!cannotStep.contains(site)) return site;
                site = bfs.bfs(rogue, circle.getEntrance2());
                if(!cannotStep.contains(site)) return site;
            }
            else{
                return leaveMonster(monster, rogue);
            }
        }
        return leaveMonster(monster,rogue);
    }

    private Site leaveMonster(Site monster, Site rogue){
        List<Site> maxSites = getStepsAwayMonster(monster, rogue);

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

    private List<Site> getStepsAwayMonster(Site monster, Site rogue){
        List<Site> maxSites = new ArrayList<Site>();
        List<Site> neighbors;
        if(dungeon.isRoom(rogue)){
            neighbors = dungeon.getNeighborsInRoom(rogue);
        }
        else{
            neighbors = dungeon.getNeighborsInCorridor(rogue);
        }
        int maxStep = -1;
        neighbors.add(rogue); // also test rogue original site
        for(Site neighbor: neighbors){
            if(!dungeon.isLegalMove(rogue, neighbor)) continue; // Test weather it is legal
            bfs.bfs(monster, neighbor);
            int neighborStep = bfs.GetStepCount();
            if(neighborStep > maxStep){
                maxSites.clear();
                maxSites.add(neighbor);
                maxStep = neighborStep;
            }
            else if(neighborStep == maxStep){
                maxSites.add(neighbor);
            }
        }
        return maxSites;
    }

    private List<Site> getStepsCloseMonster(Site monster, Site rogue){
        List<Site> minSites = new ArrayList<Site>();
        List<Site> neighbors;
        if(dungeon.isRoom(rogue)){
            neighbors = dungeon.getNeighborsInRoom(rogue);
        }
        else{
            neighbors = dungeon.getNeighborsInCorridor(rogue);
        }
        int minStep = 2*N;
        neighbors.add(rogue); // also test rogue original site
        for(Site neighbor: neighbors){
            if(!dungeon.isLegalMove(rogue, neighbor)) continue; // Test weather it is legal
            bfs.bfs(monster, neighbor);
            int neighborStep = bfs.GetStepCount();
            if(neighborStep < minStep){
                minSites.clear();
                minSites.add(neighbor);
                minStep = neighborStep;
            }
            else if(neighborStep == minStep){
                minSites.add(neighbor);
            }
        }
        return minSites;
    }
}
