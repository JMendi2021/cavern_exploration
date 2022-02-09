import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;
    static LocationMap locationMap = new LocationMap();
    HashMap<String, String> vocabulary = new HashMap<>();
    FileLogger fileLogger = new FileLogger();
    ConsoleLogger consoleLogger = new ConsoleLogger();


    public Mapping() {
        vocabulary.put("QUIT", "Q");
        vocabulary.put("UP", "U");
        vocabulary.put("DOWN", "D");
        vocabulary.put("NORTH", "N");
        vocabulary.put("EAST", "E");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("NORTHEAST", "NE");
        vocabulary.put("NORTHWEST", "NW");
        vocabulary.put("SOUTHEAST", "SE");
        vocabulary.put("SOUTHWEST", "SW");
    }

    public void mapping() {
        Scanner sc = new Scanner(System.in);
        int location = INITIAL_LOCATION;

        while (true) {
            String description = locationMap.get(location).getDescription();
            consoleLogger.log(description);
            fileLogger.log(description);

            if (locationMap.get(location).getLocationId() == 0) break;

            Map<String, Integer> exits = locationMap.get(location).getExits();

            StringBuilder exitStr = new StringBuilder(), exitMessage = new StringBuilder();
            for (String i : exits.keySet()) {
                exitStr.append(i).append(", ");
            }
            exitMessage.append("Available exits are ").append(exitStr);
            consoleLogger.log(exitMessage.toString());
            fileLogger.log(exitMessage.toString());
            String input = sc.nextLine().toUpperCase();


            String[] inputArr = input.split(" ");
            String direction = null;
            if (inputArr.length == 1) {
                if (vocabulary.containsKey(input)) direction = vocabulary.get(input);
                else if (vocabulary.containsValue(input)) direction = input;
            } else {
                for (String word : inputArr) {
                    for (String key : vocabulary.keySet()) {
                        if (word.contains(key)) {
                            if (vocabulary.containsKey(key)) direction = vocabulary.get(word);
                            else if (vocabulary.containsValue(key)) direction = word;
                        }
                    }
                }
            }

            String unreachable = "You cannot go in that direction";
            if (exits.containsKey(direction))
                location = locationMap.get(exits.get(direction)).getLocationId();
            else {
                consoleLogger.log(unreachable);
                fileLogger.log(unreachable);
            }
        }
    }

    public static void main(String[] args) {
        Mapping mapping = new Mapping();
        mapping.mapping();
    }
}
