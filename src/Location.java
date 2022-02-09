import java.util.LinkedHashMap;
import java.util.Map;

public class Location {
    private final int locationId;
    private final String description;
    private final LinkedHashMap<String, Integer> exits;



    public Location(int locationId, String description, Map<String, Integer> exits) {
        this.locationId = locationId;
        this.description = description;
        if (exits != null) {
            this.exits = (LinkedHashMap<String, Integer>) exits;
        }
        else {
            this.exits = new LinkedHashMap<>();
            this.exits.put("Q", 0);
        }
    }

    protected void addExit(String direction, int location) {
        exits.put(direction, location);
    }

    public int getLocationId() {
        return locationId;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return exits;
    }
}
