package model.colony.antnest;

import model.datastructures.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chamber in the ant nest.
 * Acts as a node in the nest graph.
 */
public class Chamber {
    private final String id;
    private final Position position;
    private final ChamberType type;
    private final List<Tunnel> tunnels;

    
    //TODO: Keep track of tiles occupied by the chamber
    private int radius;
    public static final int DEFAULT_RADIUS = 4;
    public static final int MIN_RADIUS = 4;
    private List<Position> tilePositions;
    private int capaticy; //TODO: Have this as an entity list of e.g. food items, larva, corpses
    
    /**
     * Constructor for Chamber with default radius.
     * @param id : chamber identifier
     * @param position : center position of the chamber
     * @param type : type of chamber
     */
    public Chamber(String id, Position position, ChamberType type) {
        this(id, position, type, DEFAULT_RADIUS);
    }
    
    /**
     * Constructor for Chamber with custom radius.
     * @param id : chamber identifier
     * @param position : center position of the chamber
     * @param type : type of chamber
     * @param radius : radius of the chamber in tiles
     */
    public Chamber(String id, Position position, ChamberType type, int radius) {
        this.id = id;
        this.position = position;
        this.type = type;
        this.radius = Math.max(radius, MIN_RADIUS);
        this.tunnels = new ArrayList<>();
    }

    /**
     * Add a tunnel connection to this chamber.
     * @param tunnel : The tunnel to add.
     */
    public void addTunnel(Tunnel tunnel) {
        tunnels.add(tunnel);
    }

    /**
     * Remove a tunnel connection from this chamber.
     * @param tunnel : The tunnel to remove.
     */
    public void removeTunnel(Tunnel tunnel) {
        tunnels.remove(tunnel);
    }
    
    /**
     * Gets a list of all chambers connected to this chamber via tunnels.
     * @return A list of connected chambers.
     */
    public List<Chamber> getConnectedChambers() {
        List<Chamber> neighbors = new ArrayList<>();
        for (Tunnel tunnel : tunnels) {
            neighbors.add(tunnel.getOther(this));
        }
        return neighbors;
    }
    
    /**
     * Check if the chamber needs expansion based on required size.
     * @param requiredSize : The minimum size needed.
     * @return True if the chamber is smaller than required.
     */
    public boolean needsExpansion(int requiredSize) {
        return getSize() < requiredSize;
    }
    
    /**
     * Expand the chamber by increasing its radius.
     * @param amount : How much to increase the radius by.
     */
    public void expand(int amount) {
        this.radius += amount;
    }
    
    /**
     * Set the chamber radius directly.
     * @param radius : The new radius (will be at least MIN_RADIUS).
     */
    public void setRadius(int radius) {
        this.radius = Math.max(radius, MIN_RADIUS);
    }

    /**
     * Check if this chamber is directly connected to another chamber.
     * @param other : The other chamber to check.
     * @return True if connected, false otherwise.
     */
    public boolean isConnectedTo(Chamber other) {
        return getConnectedChambers().contains(other);
    }
    
    public String getId() { return id; }
    public Position getPosition() { return position; }
    public ChamberType getType() { return type; }
    public List<Tunnel> getTunnels() { return tunnels; }
    public int getRadius() { return radius; }
    public int getSize() {
        int diameter = 2 * radius + 1;
        return diameter * diameter;
    }
}
