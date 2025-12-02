package model;

import model.ants.status.Status;
import model.datastructures.Position;
import model.world.Item;
import model.world.MaterialType;
import model.world.Tile;
import model.world.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveFileLoader {
    private static SaveFileLoader INSTANCE;

    private SaveFileLoader(){

    }


    /**
     * Takes the name of a savefile in .json format and loads it into the game.
     * The method was coded by a human, then refactored by an AI co-pilot and then tested by a human
     * to ensure the functionality was identical.
     * @param saveName the name of the savefile, without hte .json extension
     * @return The newly loaded world
     * @throws IOException Something went wrong during loading, perhaps due to a corrupted save file
     */
    public World load(String saveName) throws IOException {
        // Read file into JSONObject
        String content = Files.readString(Paths.get(saveName + ".json"));
        JSONObject json = new JSONObject(content);

        System.out.println(json);

        World loadedWorld = new World();

        // Load tiles
        JSONArray tiles = json.getJSONArray("tiles");

        for (int i = 0; i < tiles.length(); i++) {
            JSONObject obj = tiles.getJSONObject(i);
            String material = obj.getString("materialType");
            MaterialType materialType;
            materialType = MaterialType.valueOf(material);
            // Create and add tile
            loadedWorld.addTile(new Tile(
                    obj.getInt("x"),
                    obj.getInt("y"),
                    materialType
            ));
        }

        //Load entities
        JSONArray entities = json.getJSONArray("entities");
        for (Object obj : entities){
            JSONObject entity = (JSONObject) obj;

            // Load Entity properties
            EntityType entityType = EntityType.valueOf(entity.getString("entityType"));
            Position position = new Position(entity.getInt("x"), entity.getInt("y"));
            int movementInterval = entity.getInt("movementInterval");

            switch (entityType){
                case ITEM -> {
                    // Load Item properties and add it to the world
                    MaterialType materialType = MaterialType.valueOf(entity.getString("materialType"));
                    loadedWorld.getEntities().add(new Item(position, materialType));
                }
                case BEING -> {
                    // Load Being properties
                    float health = entity.getFloat("health");
                    float maxHealth = entity.getFloat("maxHealth");
                    float hunger = entity.getFloat("hunger");
                    float maxHunger = entity.getFloat("maxHunger");
                    int age = entity.getInt("age");
                    float hungerDepletionRate = entity.getFloat("hungerDepletionRate");
                    List<Status> statuses = new ArrayList<>();
                    //TODO add statuses
                    BeingType beingType = BeingType.valueOf(entity.getString("beingType"));
                    switch(beingType){
                        case ANT -> {
                            // Load Ant properties
                            AntType antType = AntType.valueOf(entity.getString("antType"));
                        }
                        default -> {}
                    }


                    //TODO
                }
                default ->{}
            }
        }

        return loadedWorld;
    }


    public static SaveFileLoader getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new SaveFileLoader();
        }
        return INSTANCE;
    }
}
