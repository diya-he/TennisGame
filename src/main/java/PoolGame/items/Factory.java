package PoolGame.items;

import PoolGame.ConfigReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class Factory {
    public static Balls createBalls() throws IOException, ParseException {
        List jsonballs = ConfigReader.praseBall();
        return new Balls(jsonballs);
    }
    public static Table createTable() throws IOException, ParseException {
        JSONObject jsonTable = ConfigReader.parseTable();
        String tableColour = (String) jsonTable.get("colour");
        Long tableX = (Long) ((JSONObject) jsonTable.get("size")).get("x");
        Long tableY = (Long) ((JSONObject) jsonTable.get("size")).get("y");
        Double tableFriction = (Double) jsonTable.get("friction");
        return new Table(tableX, tableY, tableFriction, tableColour);
    }
}
