package model.colony.antnest;

import model.datastructures.Position;
import java.util.*;

/**
 * Represents an ant nest as a graph of chambers (nodes) 
 * connected by tunnels (edges).
 */
public class AntNest {
    private List<Chamber> chambers;
    private List<Tunnel> tunnels;
    private Chamber entrance;

    /** Minimum distance required between any two chambers in tiles. */
    public static final int MIN_CHAMBER_DISTANCE = 20;
    
    public AntNest() {
        this.chambers = new ArrayList<>();
        this.tunnels = new ArrayList<>();
    }
    
    /**
     * Add a chamber to the nest.
     * @throws IllegalArgumentException if the chamber is too close to an existing chamber
     */
    public void addChamber(Chamber chamber) {
        for (Chamber existing : chambers) {
            if (distanceBetween(chamber.getPosition(), existing.getPosition()) < MIN_CHAMBER_DISTANCE) {
                throw new IllegalArgumentException(
                    "Chamber '" + chamber.getId() + "' is too close to chamber '" + existing.getId() + 
                    "'. Minimum distance is " + MIN_CHAMBER_DISTANCE + " tiles."
                );
            }
        }
        chambers.add(chamber);
    }
    
    /**
     * Calculate Manhattan distance between two positions.
     */
    private int distanceBetween(Position a, Position b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
    
    public void removeChamber(Chamber chamber) {
        chambers.remove(chamber);
    }
    
    /**
     * Create a tunnel connecting two chambers.
     */
    public Tunnel connect(Chamber a, Chamber b) {
        Tunnel tunnel = new Tunnel(a, b);
        tunnels.add(tunnel);
        a.addTunnel(tunnel);
        b.addTunnel(tunnel);
        return tunnel;
    }
    
    /**
     * Check if a path exists between two chambers using BFS.
     * Only considers passable tunnels.
     */
    public boolean isReachable(Chamber from, Chamber to) {
        if (from.equals(to)) return true;
        
        Set<Chamber> visited = new HashSet<>();
        Queue<Chamber> queue = new LinkedList<>();
        queue.add(from);
        visited.add(from);
        
        while (!queue.isEmpty()) {
            Chamber current = queue.poll();
            
            for (Tunnel tunnel : current.getTunnels()) {
                if (!tunnel.isPassable()) continue;
                
                Chamber neighbor = tunnel.getOther(current);
                if (neighbor.equals(to)) return true;
                
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }
    
    /**
     * Find all tunnels that are blocked or need digging.
     */
    public List<Tunnel> findBlockedTunnels() {
        List<Tunnel> blocked = new ArrayList<>();
        for (Tunnel tunnel : tunnels) {
            if (!tunnel.isPassable()) {
                blocked.add(tunnel);
            }
        }
        return blocked;
    }
    
    /**
     * Find tunnels that need work to restore connectivity from a starting chamber.
     * Useful for generating dig/clear tasks.
     * @param start : The chamber from which to check connectivity.
     */
    public List<Tunnel> findTunnelsNeedingWork(Chamber start) {
        // First, find all reachable chambers
        Set<Chamber> reachable = new HashSet<>();
        Queue<Chamber> queue = new LinkedList<>();
        queue.add(start);
        reachable.add(start);
        
        while (!queue.isEmpty()) {
            Chamber current = queue.poll();
            for (Tunnel tunnel : current.getTunnels()) {
                if (!tunnel.isPassable()) continue;
                Chamber neighbor = tunnel.getOther(current);
                if (!reachable.contains(neighbor)) {
                    reachable.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        
        // Find tunnels that would connect unreachable chambers
        List<Tunnel> needsWork = new ArrayList<>();
        for (Chamber chamber : chambers) {
            if (!reachable.contains(chamber)) {
                for (Tunnel tunnel : chamber.getTunnels()) {
                    if (!tunnel.isPassable() && !needsWork.contains(tunnel)) {
                        needsWork.add(tunnel);
                    }
                }
            }
        }
        return needsWork;
    }
    
    public List<Chamber> getChambers() { return chambers; }
    public List<Tunnel> getTunnels() { return tunnels; }
    public Chamber getEntrance() { return entrance; }
    public void setEntrance(Chamber entrance) { this.entrance = entrance; }
}
