import java.util.List;

public class Circle {
    private Site entrance1;
    private Site entrance2;
    private List<Site> corridorPath;
    public Circle(Site entrance1, Site entrance2, List<Site> corridorPart){
        this.entrance1 = entrance1;
        this.entrance2 = entrance2;
        this.corridorPath = corridorPart;
//        for(Site site: corridorPart){
//            System.out.println("path item:"+site.i()+"|"+site.j());
//        }
    }

    public Site getEntrance1(){return entrance1;}
    public Site getEntrance2(){return entrance2;}
    public List<Site> getCorridorPart(){return corridorPath;}
    public boolean isInCorridor(Site target){
        for(Site site: corridorPath){
            if(site.equals(target)){
                return true;
            }
        }
        return false;
    }

}
