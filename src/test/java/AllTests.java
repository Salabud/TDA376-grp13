import model.algorithms.Pathfinding;
import model.colony.tasks.Task;
import model.datastructures.Position;
import model.entity.Entity;
import model.entity.being.ants.WorkerAnt;
import model.entity.being.ants.TaskPerformerAnt;
import model.entity.being.ants.movement.PathfindingMovement;
import model.entity.item.Item;
import model.world.MaterialType;
import model.world.Tile;
import model.world.WorldContext;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AllTests {

    @Test
    void pathfindingAvoidsBlocksIn10x10() {
        final int size = 10;
        Tile[][] grid = new Tile[size][size];

        addWallWithGap(grid, 2, 7, 4, 5);

        Position start = new Position(0, 0);
        Position goal = new Position(9, 9);

        Position[] path = Pathfinding.Astar(start, goal, grid);

        assertTrue(path.length > 0, "Path should be found");
        assertPathOnPassableTiles(path, grid);
        assertAdjacent(path[0], start);
        assertAdjacent(path[path.length - 1], goal);
    }

    @Test
    void antCarriesItemAlongPath() {
        final int size = 10;
        Tile[][] tiles = new Tile[size][size];
        @SuppressWarnings("unchecked")
        List<Entity>[][] entityGrid = new List[size][size];
        initEntityGrid(entityGrid);

        WorldContext ctx = new WorldContext(new ArrayList<>(), entityGrid, new ArrayList<>(), tiles, size);

        Position start = new Position(1, 1);
        Position target = new Position(7, 7);

        Position[] expectedPath = Pathfinding.Astar(start, target, tiles);
        assertTrue(expectedPath.length > 0, "Path should be found for ant movement");

        WorkerAnt ant = new WorkerAnt(start);
        ant.setWorldContext(ctx);
        ant.assignTask(new NoOpTask(target));

        Item item = new Item(new Position(1, 2), MaterialType.FOOD, 1);
        ant.attemptCarry(item);
        assertTrue(ant.isCarrying(), "Ant should pick up adjacent item");

        ant.setMovement(new PathfindingMovement(ant.getPosition(), target, tiles));

        // Walk the path plus a small buffer to ensure completion and item catch-up.
        int stepsToRun = expectedPath.length + 3;
        for (int i = 0; i < stepsToRun; i++) {
            ant.update();
        }

        Position expectedAntPos = expectedPath[expectedPath.length - 1];
        assertEquals(expectedAntPos, ant.getPosition(), "Ant should end on the last step of the path");
        assertEquals(ant.getPosition(), item.getPosition(), "Carried item should follow the ant");
        assertAdjacent(ant.getPosition(), target);
    }

    private void addWallWithGap(Tile[][] grid, int xStart, int xEnd, int y, int gapX) {
        for (int x = xStart; x <= xEnd; x++) {
            if (x == gapX) continue;
            grid[x][y] = new Tile(x, y, MaterialType.DIRT);
        }
    }

    private void assertPathOnPassableTiles(Position[] path, Tile[][] grid) {
        for (Position step : path) {
            assertNull(grid[step.getX()][step.getY()], "Path steps must be on passable tiles");
        }
    }

    private void assertAdjacent(Position a, Position b) {
        int manhattan = Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
        assertEquals(1, manhattan, "Positions should be adjacent");
    }

    private void initEntityGrid(List<Entity>[][] entityGrid) {
        int size = entityGrid.length;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                entityGrid[x][y] = new ArrayList<>();
            }
        }
    }

    /**
     * Keeps the ant non-idle so ant does not walk away after a task. 
     */
    private static class NoOpTask extends Task {
        private final Position target;

        NoOpTask(Position target) {
            this.target = target;
        }

        @Override
        public void execute(TaskPerformerAnt ant) {
            // Intentionally no-op; task never completes.
        }

        @Override
        public int getPriority() {
            return 0;
        }

        @Override
        public Position getTargetLocation() {
            return target;
        }

        @Override
        public String getDescription() {
            return "No-op test task";
        }
    }
}
