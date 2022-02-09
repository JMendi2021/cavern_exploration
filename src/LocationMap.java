import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME =  "locations.txt";
    private static final String DIRECTIONS_FILE_NAME =  "directions.txt";
    static HashMap<Integer, Location> locations = new HashMap<>();

    static {
        FileLogger fileLogger = new FileLogger();
        ConsoleLogger consoleLogger = new ConsoleLogger();

        try {
            String message, line;
            String[] locationArr;
            BufferedReader reader = new BufferedReader(new FileReader(LOCATIONS_FILE_NAME));
            consoleLogger.log("Available locations:"); fileLogger.log("Available locations:");
            while ((line = reader.readLine()) != null) {
                locationArr = line.split(",", 2);
                message = locationArr[0] + ": " + locationArr[1];
                consoleLogger.log(message);
                fileLogger.log(message);
                locations.put(Integer.parseInt(locationArr[0]), new Location(Integer.parseInt(locationArr[0]), locationArr[1], null));
            }
        } catch (IOException e) {
            consoleLogger.log("IOException error");
            fileLogger.log("IOException error");
        }

        try {
            String message, line;
            String[] direction;
            BufferedReader reader = new BufferedReader(new FileReader(DIRECTIONS_FILE_NAME));
            consoleLogger.log("Available directions:"); fileLogger.log("Available directions:");
            while ((line = reader.readLine()) != null) {
                direction = line.split(",");
                message = direction[0] + ": " + direction[1] + ": " + direction[2];
                consoleLogger.log(message);
                fileLogger.log(message);
                locations.get(Integer.parseInt(direction[0])).addExit(direction[1], Integer.parseInt(direction[2]));
            }
        } catch (IOException e) {
            consoleLogger.log("IOException error");
            fileLogger.log("IOException error");
        }
    }

    @Override
    public int size() {
        int count = 0;
        for (Map.Entry<Integer, Location> ignored : locations.entrySet())
            count++;
        return count;
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        for (Map.Entry<Integer, Location> e : locations.entrySet()) {
            if (e.getKey() == key)
                return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Map.Entry<Integer, Location> e : locations.entrySet()) {
            if (e.getValue() == value)
                return true;
        }
        return false;
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        locations.putAll(m);
    }

    @Override
    public void clear() {
        for (Map.Entry<Integer, Location> e : locations.entrySet()) {
            locations.remove(e.getKey());
        }
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
