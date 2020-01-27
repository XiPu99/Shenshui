package com.xipu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.Arrays;

public class Tester {

    public void test(){
        JsonReader json = new JsonReader();
        JsonValue value = json.parse(Gdx.files.internal("test.json"));
        System.out.println(value.getInt("width"));
        System.out.println(value.getInt("height"));
        JsonValue child = value.getChild("map");

        while(child != null){
            System.out.println(Arrays.toString(child.asIntArray()));
            child = child.next();
        }
    }

    public static void main(String[] args) {


    }
}
