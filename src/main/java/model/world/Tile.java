package model.world;

import model.datastructures.Position;
import org.json.JSONObject;

/**
 * Represents a static tile in the world grid with a specific material type.
 */
public class Tile {
    private Position position;
    private MaterialType materialType;

    public Tile(int x, int y, MaterialType materialType){
        this.materialType = materialType;
        this.position = new Position(x,y);
    }

    public Tile(Position position, MaterialType materialType){
        this.materialType = materialType;
        this.position = position;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public Position getPosition() {
        return position;
    }

    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("x", position.getX());
        obj.put("y", position.getY());
        obj.put("materialType", materialType);
        return obj;
    }

    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }

    public Item toItem(){return new Item(this.position, this.materialType);}
}
