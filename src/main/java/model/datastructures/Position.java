package model.datastructures;

/**
 * Represents a position in a 2D space with x and y coordinates. Not in terms of pixels.
 */
public class Position {
    private int x;
    private int y;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }
    
    /**
     * Check if this position is adjacent to another position.
     * Adjacent means Manhattan distance of 1 (directly up, down, left, or right).
     * 
     * @param other the position to check adjacency with
     * @return true if the positions are adjacent
     */
    public boolean isAdjacentTo(Position other) {
        int dx = Math.abs(this.x - other.x);
        int dy = Math.abs(this.y - other.y);
        return (dx + dy) == 1;
    }
}
