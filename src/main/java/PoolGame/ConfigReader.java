package PoolGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigReader {
	private static final String path = "src/main/resources/config.json";
	public static List praseBall() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader(path));
		JSONObject jsonObject = (JSONObject) object;
		JSONObject jsonBalls = (JSONObject) jsonObject.get("Balls");
		List jsonballs = (List) jsonBalls.get("ball");
		return jsonballs;
	}
	public static JSONObject parseTable() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader(path));
		JSONObject jsonObject = (JSONObject) object;
		JSONObject jsonTable = (JSONObject) jsonObject.get("Table");
		return jsonTable;
	}

}