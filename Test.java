public class Test {
    public Test() {
    }

    public static void Test1(){
        String s = "/Dungeons/dungeonB.txt";
        In stdin = new In(s);
        Game game = new Game(stdin);
        Dungeon dungeon = game.getDungeon();
        Site monster = game.getMonsterSite();
        System.out.println(dungeon.getEntrances());
    }

    public static void main(String[] args) {
        Test1();

    }
}
