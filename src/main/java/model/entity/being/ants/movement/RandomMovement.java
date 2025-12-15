package model.entity.being.ants.movement;

import model.algorithms.Pathfinding;
import model.entity.being.ants.Ant;
import model.datastructures.Position;
import model.world.Tile;

import java.util.*;

/**
 * Implements a movement strategy where the ant picks a random reachable goal
 * and pathfinds to it. Uses BFS to find actually reachable positions.
 */
public class RandomMovement implements AntMovement {
    private static final int DEFAULT_WANDER_RADIUS = 30;
    private static final int MIN_DISTANCE = 10; // Prefer goals at least this far away
    
    private int currentStep = 0;
    private Position[] pathToGoal;
    private Position goal;

    /**
     * Create a RandomMovement with a random goal within the default radius.
     * @param ant : the ant that will move
     * @param tileGrid : the tile grid for pathfinding
     */
    public RandomMovement(Ant ant, Tile[][] tileGrid) {
        this(ant, tileGrid, DEFAULT_WANDER_RADIUS);
    }
    
    /**
     * Create a RandomMovement with a random goal within the specified radius.
     * @param ant : the ant that will move
     * @param tileGrid : the tile grid for pathfinding
     * @param wanderRadius : the maximum distance from current position to pick a goal
     */
    public RandomMovement(Ant ant, Tile[][] tileGrid, int wanderRadius) {
        findReachableGoal(ant.getPosition(), tileGrid, wanderRadius);
    }
    
    /**
     * We use BFS to find all reachable positions within the radius,
     * then pick a random one (preferring farther positions).
     */
    private void findReachableGoal(Position start, Tile[][] tileGrid, int maxRadius) {
        int gridWidth = tileGrid.length;
        int gridHeight = tileGrid[0].length;
        final int skyLimit = 19; //prevent ants from flying

        List<Position> farPositions = new ArrayList<>();
        List<Position> nearPositions = new ArrayList<>();
        
        Set<String> visited = new HashSet<>();
        Queue<Position> queue = new LinkedList<>();
        
        queue.add(start);
        visited.add(key(start));
        
        while (!queue.isEmpty()) {
            Position current = queue.poll();
            int distance = manhattanDistance(start, current);

            if (distance > maxRadius) continue;

            if (distance > 0) {
                if (distance >= MIN_DISTANCE) {
                    farPositions.add(current);
                } else {
                    nearPositions.add(current);
                }
            }
            
            // Explore neighbors
            int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : dirs) {
                int nx = current.getX() + dir[0];
                int ny = current.getY() + dir[1];
                String nKey = nx + "," + ny;
                
                if (nx >= 0 && nx < gridWidth && ny >= skyLimit && ny < gridHeight
                        && !visited.contains(nKey)
                        && tileGrid[nx][ny] == null) {
                    visited.add(nKey);
                    queue.add(new Position(nx, ny));
                }
            }
        }
        
        // Pick a random position from the reachable ones (staying in the same place if no position found)
        Position chosen;
        if (!farPositions.isEmpty()) {
            chosen = farPositions.get((int)(Math.random() * farPositions.size()));
        } else if (!nearPositions.isEmpty()) {
            chosen = nearPositions.get((int)(Math.random() * nearPositions.size()));
        } else {
            this.goal = start;
            this.pathToGoal = new Position[0];
            return;
        }
        
        this.goal = chosen;
        this.pathToGoal = Pathfinding.Astar(start, chosen, tileGrid);
    }
    
    private int manhattanDistance(Position a, Position b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
    
    private String key(Position p) {
        return p.getX() + "," + p.getY();
    }

    @Override
    public void move(Ant ant) {
        if (isComplete()) return;
        
        ant.setPosition(pathToGoal[currentStep]);
        currentStep++;
    }
    
    @Override
    public boolean isComplete() {
        return pathToGoal == null || currentStep >= pathToGoal.length;
    }
    
    /**
     * Get the goal position this movement is heading towards.
     */
    public Position getGoal() {
        return goal;
    }
}
