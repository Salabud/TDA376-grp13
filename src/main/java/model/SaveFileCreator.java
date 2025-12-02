package Model;

import Model.World.Tile;
import Model.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class SaveFileCreator {
    private static SaveFileCreator INSTANCE;

    private SaveFileCreator(){
    }

    public void save(World world, String saveName){
        JSONObject json = new JSONObject();
        JSONArray tileArray = new JSONArray();
        for (Tile tile : world.getTiles()){
            JSONObject tileObject = new JSONObject();
            tileObject.put("Material", tile.getMaterialType().toString());
            tileObject.put("X", tile.getPosition().getX());
            tileObject.put("Y", tile.getPosition().getY());
            tileArray.put(tileObject);
        }
        json.put("tiles", tileArray);
        try(FileWriter fileWriter = new FileWriter(saveName + ".json")) {
            fileWriter.write(json.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SaveFileCreator getInstance(){
        if(INSTANCE == null){
            INSTANCE = new SaveFileCreator();
        }
        return INSTANCE;
    }

}
