package CS230.items;


import javafx.scene.image.Image;
/**
 * Bomb item class for the game, is a subclass of item
 * @author Tom Stevens
 * @author Marek Jezinski
 * @version 1.0
 */
public class Bomb extends Item {
    private int secondsToExplode = -2;
    /**
     *
     * Bomb
     *
     * @param img  the image of the bomb
     * @param x  the x coordinate
     * @param y  the y coordinate
     */
    public Bomb(Image img, int x, int y) {
        super(img, x, y);
    }



    /**
     * method that gets the seconds that a bomb will last before exploding
     * @return secondsToExplode seconds to explosion
     */
    public int getSecondsToExplode() {
        return secondsToExplode;
    }

    /**
     * method that sets a bomb's seconds to explode
     * @param secondsToExplode setting bomb explosion time
     */
    public void setSecondsToExplode(int secondsToExplode) {
        this.secondsToExplode = secondsToExplode;
    }

    /**
     * method that returns true or false if whether the player's coordinates is the same as a bomb.
     * checks whether a player is next to a bomb or not.
     * @param playerX  the player X coordinate
     * @param playerY  the player Y coordinate
     * @return true if player is next to a bomb, false otherwise.
     */
    public boolean isNextToBomb(int playerX, int playerY) {
        if ((x == playerX - 1 || x == playerX + 1 )&& y == playerY) {
            return true;
        }
        else return (x == playerX && (y == playerY + 1 || y == playerY - 1));
    }
}
