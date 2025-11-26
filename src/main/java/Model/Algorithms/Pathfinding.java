package Model.Algorithms;

import Model.Datastructures.Position;

import java.util.*;

public class Pathfinding {

    // Replace this with your actual world reference (null = passable)
    public static Object[][] world;

    public static Position[] Astar(Position start, Position goal) {
        Set<String> closed = new HashSet<>();

        Map<String, Double> gScore = new HashMap<>();
        gScore.put(key(start), 0.0);

        Map<String, Double> fScore = new HashMap<>();
        fScore.put(key(start), heuristic(start, goal));

        Map<String, Position> cameFrom = new HashMap<>();

        PriorityQueue<Position> open = new PriorityQueue<>(
                Comparator.comparingDouble(pos -> fScore.getOrDefault(key(pos), Double.POSITIVE_INFINITY))
        );
        open.add(start);

        while (!open.isEmpty()) {

            Position current = open.poll();
            String currentKey = key(current);

            if (!fScore.getOrDefault(currentKey, Double.POSITIVE_INFINITY)
                    .equals(heuristic(current, goal) + gScore.getOrDefault(currentKey, Double.POSITIVE_INFINITY))) {
                continue;
            }

            if (current.equals(goal)) {
                return reconstruct(current, cameFrom);
            }

            closed.add(currentKey);

            for (Position neighbor : neighbors(current)) {

                String nk = key(neighbor);

                if (closed.contains(nk)) {
                    continue;
                }

                double tentativeG = gScore.get(currentKey) + 1;

                if (tentativeG < gScore.getOrDefault(nk, Double.POSITIVE_INFINITY)) {

                    cameFrom.put(nk, current);
                    gScore.put(nk, tentativeG);
                    fScore.put(nk, tentativeG + heuristic(neighbor, goal));

                    open.add(neighbor);
                }
            }
        }

        return new Position[0];
    }

    // Heuristic for grid
    private static double heuristic(Position a, Position b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private static String key(Position p) {
        return p.getX() + "," + p.getY();
    }

    private static boolean passable(int x, int y) {
        return world[x][y] == null;
    }

    private static boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 &&
                x < world.length &&
                y < world[0].length;
    }

    private static List<Position> neighbors(Position p) {
        List<Position> list = new ArrayList<>();
        int x = p.getX();
        int y = p.getY();

        int[][] dirs = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        for (int[] d : dirs) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (inBounds(nx, ny) && passable(nx, ny)) {
                list.add(new Position(nx, ny));
            }
        }

        return list;
    }

    private static Position[] reconstruct(Position current, Map<String, Position> cameFrom) {
        List<Position> path = new ArrayList<>();
        path.add(current);

        while (cameFrom.containsKey(key(current))) {
            current = cameFrom.get(key(current));
            path.add(current);
        }

        Collections.reverse(path);

        // Removing the first and last node. The ant already starts on one and the ant doesn't
        // want to end up in the tile it is going to go to.
        if (path.size() >= 2) {
            path = path.subList(1, path.size() - 1);
        } else {
            return new Position[0];
        }

        return path.toArray(new Position[0]);
    }
}
