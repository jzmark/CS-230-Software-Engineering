package com.example.cs230;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class MapReader {
    private Tile[][] tiles = null;
    private int maxTileX;
    private int maxTileY;

    private int x;
    private int y;
    private ArrayList<Integer> clockParam = new ArrayList<Integer>();
    String fileName;
    public MapReader(String fileName) {
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
                    tiles[x][y] = new Tile(in.next());
                }
            }
            while (in.hasNext()) {
                if (in.next().equals("Clock")) {
                    x = in.nextInt();
                    if (x%2==0){
                        this.clockParam.add(x);
                    }
                    else{
                        this.clockParam.add(x-1);
                    }
                    y = in.nextInt();
                    if (y%2==0){
                        this.clockParam.add(y);
                    }
                    else{
                        this.clockParam.add(y-1);
                    }
                }
            }


        }
        catch(Exception e) {
           System.err.println("Please check map file!");
           System.exit(1);
        }
        in.close();
    }
    public int getMaxTileX() {
        return maxTileX;
    }
    public int getMaxTileY() {
        return maxTileY;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

   public ArrayList<Integer> getClock(){
        return this.clockParam;
    }
    }

