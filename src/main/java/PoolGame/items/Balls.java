package PoolGame.items;

import PoolGame.Drawable;
import javafx.scene.shape.Line;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Balls{
    HashMap<Integer, Ball> balls = new HashMap<>();
    public Balls() throws IOException, ParseException {
        String path = "src/main/resources/config.json";
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(path));
        JSONObject jsonObject = (JSONObject) object;
        JSONObject jsonBalls = (JSONObject) jsonObject.get("Balls");
        List jsonballs = (List) jsonBalls.get("ball");
        for(int i=0; i < jsonballs.size(); i++){
            JSONObject attribute = (JSONObject) jsonballs.get(i);
            this.balls.put(i, new Ball((String) attribute.get("colour"),
                    (Double) attribute.get("mass"),
                    (Double) ((JSONObject)attribute.get("position")).get("x"),
                    (Double) ((JSONObject)attribute.get("position")).get("y"),
                    (Double)((JSONObject)attribute.get("velocity")).get("x"),
                    (Double)((JSONObject)attribute.get("velocity")).get("y")));
        }
//        for (Integer key: this.balls.keySet()) {
//            registerMouseAction(this.balls.get(key));
//        }
    }

    public HashMap<Integer, Ball> getBalls() {
        return this.balls;
    }

}
