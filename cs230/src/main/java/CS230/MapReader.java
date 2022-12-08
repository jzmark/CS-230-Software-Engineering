package CS230;

import CS230.items.*;
import CS230.npc.FlyingAssassin;
import CS230.npc.NPC;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class for reading the text file for levels/maps
 * @author Tom Stevens
 * @version 1.0
 */
public class MapReader {
    private Tile[][] tiles = null;
    private int maxTileX;
    private int maxTileY;
    private int timer;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Loot> loot = new ArrayList<>();
    private ArrayList<Clock> clocks = new ArrayList<>();
    private Door door;
    private Gate rgate;
    private Lever rlever;
    private Gate wgate;
    private Lever wlever;
    private Player pl;
    private int playerStartX = 0;
    private int playerStartY = 0;
    private ArrayList<Bomb> bombs = new ArrayList<>();
    private ArrayList<NPC> npcs = new ArrayList<>();
    private ArrayList<FlyingAssassin> flyingAssassins = new ArrayList<>();
    private String fileName;
    private final int CLOCK_TIME_ADDED = 20;
    private final int CENT_VALUE = 10;
    private final int DOLLAR_VALUE = 20;
    private final int RUBY_VALUE = 30;
    private final int DIAMOND_VALUE = 40;

    /**
     * Constructor for the class
     * @param fileName
     */
    public MapReader(String fileName)  {
        this.fileName = fileName;
        Scanner in = null;
        File f = new File(this.fileName);
        try {
            in = new Scanner(f);
        }
        catch(FileNotFoundException e) {
            System.err.println(String.format("Map file '%s' not found!", this.fileName));
            System.exit(0);
        }

        try {
            maxTileX = in.nextInt();
            maxTileY = in.nextInt();
            tiles = new Tile[maxTileX][maxTileY];
            for(int y = 0; y < maxTileY; y++) {
                for(int x = 0; x < maxTileX; x++) {
                    String word = in.next();
                    if(word.length() == 4) {
                        tiles[x][y] = new Tile(word);
                    } else {
                        System.err.println("Level file tile shorter/longer than 4 characters detected");
                        throw new Exception();
                    }
                }
            }
            while (in.hasNext()) {
                String type = in.next().toLowerCase();
                if (type.equals("player")) {
                    pl = new Player(in.nextInt(), in.nextInt(), new Image(getClass().getResource("player.png")
                            .toURI().toString()));
                }
                else if (type.equals("timer")) {
                    this.timer = in.nextInt();
                }
                else if (type.equals("rgate")) {
                    rgate = new Gate(new Image(getClass().getResource("rustygate.png").toURI().toString()),
                            in.nextInt(), in.nextInt());
                }
                else if (type.equals("rlever")) {
                    rlever = new Lever(new Image(getClass().getResource("rustylever.png").toURI().toString()),
                            in.nextInt(), in.nextInt());
                }
                else if (type.equals("wgate")) {
                    wgate = new Gate(new Image(getClass().getResource("woodengate.png")
                            .toURI().toString()),
                            in.nextInt(), in.nextInt());
                }
                else if (type.equals("wlever")) {
                    wlever = new Lever(new Image(getClass().getResource("woodenlever.png")
                            .toURI().toString()),
                            in.nextInt(), in.nextInt());
                }
                //
                else if (type.equals("clock")) {
                    Clock c = new Clock(new Image(getClass().getResource("clock.png").toURI().toString()),
                            in.nextInt(), in.nextInt(), CLOCK_TIME_ADDED);
                    items.add(c);
                    clocks.add(c);
                }
                else if (type.equals("bomb")) {
                    Bomb b = new Bomb(new Image(getClass().getResource("bomb.png").toURI().toString()),
                            in.nextInt(), in.nextInt());
                    items.add(b);
                    this.bombs.add(b);
                }
                //TODO: Change filename for all loot!
                else if (type.equals("cent")) {
                    Cent c = new Cent(new Image(getClass().getResource("placeholder.png").toURI().toString()),
                            in.nextInt(), in.nextInt(), CENT_VALUE);
                    items.add(c);
                    loot.add(c);
                }
                else if (type.equals("dollar")) {
                    Dollar d = new Dollar(new Image(getClass().getResource("placeholder.png").toURI().toString()),
                            in.nextInt(), in.nextInt(), DOLLAR_VALUE);
                    items.add(d);
                    loot.add(d);
                }
                else if (type.equals("ruby")) {
                    Ruby r = new Ruby(new Image(getClass().getResource("placeholder.png").toURI().toString()),
                            in.nextInt(), in.nextInt(), RUBY_VALUE);
                    items.add(r);
                    loot.add(r);
                }
                else if (type.equals("diamond")) {
                    Diamond d = new Diamond(new Image(getClass().getResource("placeholder.png").toURI().toString()),
                            in.nextInt(), in.nextInt(), DIAMOND_VALUE);
                    items.add(d);
                    loot.add(d);
                }
                else if (type.equals("door")) {
                    Door d = new Door(new Image(getClass().getResource("door.png").toURI().toString()),
                            in.nextInt(), in.nextInt());
                    items.add(d);
                    door = d;
                }
                else if (type.equals("flyingassasin")) {
                    FlyingAssassin temp = new FlyingAssassin(new Image(getClass().getResource("flyingassassin.png")
                            .toURI().toString()), in.nextInt(), in.nextInt(), in.next().toLowerCase().charAt(0));
                    npcs.add(temp);
                    flyingAssassins.add(temp);
                }
                else {
                    System.err.println("Please check level file, entity identifier mismatch");
                    throw new Exception();
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            System.err.println("Please check level file!");
            System.exit(1);
        }
        in.close();
    }

    /**
     * Get method for maxTileX
     * @return maxTileX
     */
    public int getMaxTileX() {
        return maxTileX;
    }
    /**
     * Get method for maxTileY
     * @return maxTileY
     */
    public int getMaxTileY() {
        return maxTileY;
    }
    /**
     * Get method for tiles
     * @return tiles
     */
    public Tile[][] getTiles() {
        return tiles;
    }
    /**
     * Get method for timer
     * @return timer
     */
    public int getTimer(){
        return this.timer;
    }
    /**
     * Get method for items
     * @return items
     */
    public ArrayList<Item> getItems() {
        return items;
    }
    /**
     * Get method for loot
     * @return loot
     */
    public ArrayList<Loot> getLoot() {
        return loot;
    }
    /**
     * Get method for clocks
     * @return clocks
     */
    public ArrayList<Clock> getClocks() {
        return clocks;
    }
    /**
     * Get method for door
     * @return door
     */
    public Door getDoor() {
        return door;
    }

    public Gate getRGate() {
        return rgate;
    }

    public Lever getRLever() {
        return rlever;
    }
    public Gate getWGate() {
        return wgate;
    }

    public Lever getWLever() {
        return wlever;
    }
    public ArrayList<Bomb> getBomb(){
        return bombs;
    }

    public Player getPlayer() {
        return pl;
    }
}

