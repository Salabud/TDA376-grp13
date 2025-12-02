package Model;

import Model.World.MaterialType;
import Model.World.Tile;
import Model.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

        JSONArray tiles = json.getJSONArray("tiles");

        for (int i = 0; i < tiles.length(); i++) {
            JSONObject obj = tiles.getJSONObject(i);
            String material = obj.getString("Material");
            MaterialType materialType;
            try {
                materialType = MaterialType.valueOf(material);
            } catch (IllegalArgumentException e) {
                throw new IOException("Invalid material type in save: " + material);
            }
            // Create and add tile
            loadedWorld.addTile(new Tile(
                    obj.getInt("X"),
                    obj.getInt("Y"),
                    materialType
            ));
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
