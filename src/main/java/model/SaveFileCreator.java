package model;

import model.entity.Entity;
import model.world.Tile;
import model.world.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class SaveFileCreator {
    private static SaveFileCreator INSTANCE;

    private SaveFileCreator(){
    }

    public void save(Model model, String saveName){
        JSONObject json = new JSONObject();

        World world = model.getWorld();
        JSONArray tileArray = new JSONArray();
        for (Tile tile : world.getTileList()){
            tileArray.put(tile.toJSON());
        }
        json.put("tiles", tileArray);

        JSONArray entityArray = new JSONArray();
        for (Entity entity : world.getEntityList()){
            entityArray.put(entity.toJSON());
        }
        json.put("entities", entityArray);

        JSONObject colony = world.getAntColony().toJSON();
        json.put("colony", colony);

        JSONArray statuses = new JSONArray();
        json.put("statuses", statuses);

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
