import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Monster {
    private Game game;
    private Dungeon dungeon;
    private int N;

    public Monster(Game game) {
        this.game    = game;
        this.dungeon = game.getDungeon();
        this.N       = dungeon.size();
    }


    // TAKE A RANDOM LEGAL MOVE
    // YOUR TASK IS TO RE-IMPLEMENT THIS METHOD TO DO SOMETHING INTELLIGENT
    public Site move() {
        Site monster = game.getMonsterSite();
        Site rogue = game.getRogueSite();
        Dungeon dungeon = game.getDungeon();
//        List<Site> neighbors = dungeon.getNeighbors(monsterSite);
//        int shortestDistance = 3*N;
//        Site nextSite = monsterSite;
//        for(Site neighbor: neighbors){
//            int distance = neighbor.manhattanTo(rogueSite);
//            if (distance == 0) return neighbor;
//            if(distance <= shortestDistance && dungeon.isLegalMove(monsterSite,neighbor)){
//                shortestDistance = distance;
//                nextSite = neighbor;
//            }
//        }
//        return nextSite; // 如果没有找到路径，保持不动

        boolean[][] visited = new boolean[N][N]; // 记录已访问的位置
        Site[][] previous = new Site[N][N];      // 记录每个位置的前驱节点，用于回溯路径
        Queue<Site> queue = new LinkedList<>();  // 队列用于BFS

        queue.add(monster);                     // 将怪物的起始位置加入队列
        visited[monster.i()][monster.j()] = true; // 标记怪物的起始位置为已访问

        // 在房间中的8个方向（N, E, S, W, NW, NE, SW, SE）
        int[][] roomDirections = {
                {0, -1}, {1, 0}, {0, 1}, {-1, 0}, // N, E, S, W
                {1, -1}, {1, 1}, {-1, -1}, {-1, 1} // NE, SE, NW, SW
        };
        // 在走廊中的4个方向（N, E, S, W）
        int[][] corridorDirections = {
                {0, -1}, {1, 0}, {0, 1}, {-1, 0} // N, E, S, W
        };

        // 开始BFS循环
        while (!queue.isEmpty()) {
            Site current = queue.poll(); // 获取队列的头部元素

            if (current.equals(rogue)) { // 如果找到流氓的位置
                return reconstructPath(monster, current, previous); // 回溯路径并返回下一步
            }

            // 根据当前的位置类型选择相应的方向数组
            int[][] directions = dungeon.isRoom(current) ? roomDirections : corridorDirections;

            // 遍历所有可能的方向
            for (int[] dir : directions) {
                int newI = current.i() + dir[0]; // 计算新位置的行索引
                int newJ = current.j() + dir[1]; // 计算新位置的列索引
                Site neighbor = new Site(newI, newJ); // 创建新位置的Site对象

                // 检查移动是否有效
                // 判断新的Neighbor是否在Dungeon里面
                if (isValidMove(current, neighbor, visited)) {
                    queue.add(neighbor); // 将新位置加入队列
                    visited[newI][newJ] = true; // 标记新位置为已访问
                    previous[newI][newJ] = current; // 记录前驱节点
                }
            }
        }

        // 如果没有找到路径，则返回原位置
        return monster;
    }

    // 检查移动是否有效
    private boolean isValidMove(Site current, Site neighbor, boolean[][] visited) {
        int i = neighbor.i(); // 获取邻居位置的行索引
        int j = neighbor.j(); // 获取邻居位置的列索引
        if (i < 0 || j < 0 || i >= N || j >= N) return false; // 检查是否越界
        if (visited[i][j]) return false; // 检查是否已访问
        return dungeon.isLegalMove(current, neighbor); // 检查移动是否合法
    }

    // 回溯路径并返回下一步的位置
    // 它实现了根据已知路径找到下一步要移动的位置，从而实现了怪物根据 BFS 算法进行智能移动的功能。
    private Site reconstructPath(Site start, Site end, Site[][] previous) {
        Site current = end; // 从终点开始
        Site move = null;   // 记录路径中的下一步

        // 从终点回溯到起点
        while (current != null && !current.equals(start)) {
            move = current; // 更新下一步的位置
            current = previous[current.i()][current.j()]; // 移动到前驱节点
        }

        return move; // 返回路径中的下一步
    }
}