package model.colony.antnest;

import model.datastructures.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tunnel connection between two chambers.
 * Acts as an edge in the nest graph.
 * Contains a list of tile positions that need to be excavated.
 */
public class Tunnel {
    private final Chamber chamberA;
    private final Chamber chamberB;
    private boolean isBlocked;
    private boolean needsDigging;

    /**
     * The list of tile positions that make up this tunnel.
     * From Chamber A center to Chamber B center.
     */
    private final List<Position> tilePositions;
    
    /**
     * Creates a tunnel between two chambers.
     * Computes the shortest path of tile positions between them.
     * Note: Chamber distance validation should be done by AntNest.addChamber().
     */
    public Tunnel(Chamber chamberA, Chamber chamberB) {
        this.chamberA = chamberA;
        this.chamberB = chamberB;
        this.isBlocked = false;
        this.needsDigging = true;  // New tunnels always need digging
        this.tilePositions = computePath(chamberA.getPosition(), chamberB.getPosition());
    }
    
    /**
     * Compute the shortest path between two positions using straight lines.
     * Excludes the start and end positions (the chambers themselves).
     * TODO: Use Leo's A* pathfinding instead of straight paths.
     */
    private List<Position> computePath(Position start, Position end) {
        List<Position> path = new ArrayList<>();
        
        int x = start.getX();
        int y = start.getY();
        int endX = end.getX();
        int endY = end.getY();

        int dx = Integer.compare(endX, x);
        int dy = Integer.compare(endY, y);
        

        while (x != endX) {
            x += dx;
            if (x != endX || y != endY) {
                path.add(new Position(x, y));
            }
        }
        while (y != endY) {
            y += dy;
            if (x != endX || y != endY) {
                path.add(new Position(x, y));
            }
        }
        return path;
    }
    
    /**
     * Get the chamber on the other end of this tunnel.
     */
    public Chamber getOther(Chamber from) {
        return from.equals(chamberA) ? chamberB : chamberA;
    }
    
    /**
     * Check if ants can pass through this tunnel.
     */
    public boolean isPassable() {
        return !isBlocked && !needsDigging;
    }
    
    /**
     * Get the list of tile positions that make up this tunnel.
     * These are the positions that need to be excavated.
     */
    public List<Position> getTilePositions() {
        return tilePositions;
    }
    
    /**
     * Get the number of tiles in this tunnel.
     */
    public int getLength() {
        return tilePositions.size();
    }
    
    public void setBlocked(boolean blocked) { this.isBlocked = blocked; }
    public void setNeedsDigging(boolean needsDigging) { this.needsDigging = needsDigging; }
    
    public Chamber getChamberA() { return chamberA; }
    public Chamber getChamberB() { return chamberB; }
    public boolean isBlocked() { return isBlocked; }
    public boolean needsDigging() { return needsDigging; }
}
