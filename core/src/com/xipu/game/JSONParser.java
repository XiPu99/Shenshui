package com.xipu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.*;
import java.util.stream.Collectors;

public final class JSONParser {

    public static final String JSON_TILE_WIDTH_NAME = "tile-width";
    public static final String JSON_TILE_HEIGHT_NAME = "tile-height";
    public static final String JSON_MAP_NAME = "map";

    public static List<TileActor> loadMapFromJSON(String fileName) {
//        Map<int[], List<TileActor>> tileMap = new HashMap<>();

        JsonReader reader = new JsonReader();
        JsonValue jsonValue = reader.parse(Gdx.files.internal(fileName));

        int tileWidth = jsonValue.getInt(JSON_TILE_WIDTH_NAME);
        int tileHeight = jsonValue.getInt(JSON_TILE_HEIGHT_NAME);

        JsonValue mapInfo = jsonValue.getChild(JSON_MAP_NAME);
        List<TileActor> list = new ArrayList<>();
        int x = 0, y = 0;
        while (mapInfo != null) {
            int[] arr = mapInfo.asIntArray();
            List<TileActor> row =  Arrays.stream(arr)
                    .mapToObj(JSONParser::buildTile)
                    .collect(Collectors.toCollection(LinkedList::new));

            for(TileActor tileActor: row){
                tileActor.setPosition(x, y);
                x += 16 * 3;
            }
            list.addAll(row);
            mapInfo = mapInfo.next();
            y += 16 * 3;
            x = 0;
        }
        return list;
    }


    private static TileActor buildTile(int tag){
        TileActor ret = new TileActor("floor.png");
        return ret;
    }

    public static void test() {
        JsonReader json = new JsonReader();
        JsonValue value = json.parse(Gdx.files.internal("test.json"));
        System.out.println(value.getInt("width"));
        System.out.println(value.getInt("height"));
        JsonValue child = value.getChild("map");

        while (child != null) {
            System.out.println(Arrays.toString(child.asIntArray()));
            child = child.next();
        }

        JsonValue items = value.getChild("items");

        while (items != null) {
            System.out.println(items.getString("type"));
            System.out.println(Arrays.toString(items.get("position").asIntArray()));

            JsonValue block = items.getChild("block");
            while (block != null) {
                System.out.println(Arrays.toString(block.asIntArray()));
                block = block.next();
            }
            items = items.next();
        }
    }

}
