package CS230.map;

import javafx.scene.image.Image;

/**
 * Class that constructs a Cell object, which is a single component of a Tile. Tile consists of 4 cells
 * @author Marek Jezinski
 * @version 1.0
 */
public class Cell {
    private char colourCode;

    private Image cellImage;

    /**
     * method that sets a cell image's colour.
     * @param colourCode - used to identify the colour of the cell
     */
    private void setCellImage(char colourCode) {
        try {
            switch(colourCode) {
                case 'R':
                    cellImage = new Image(getClass().getResource("red.png").toURI().toString());
                    break;
                case 'G':
                    cellImage = new Image(getClass().getResource("green.png").toURI().toString());
                    break;
                case 'B':
                    cellImage = new Image(getClass().getResource("blue.png").toURI().toString());
                    break;
                case 'Y':
                    cellImage = new Image(getClass().getResource("yellow.png").toURI().toString());
                    break;
                case 'O':
                    cellImage = new Image(getClass().getResource("orange.png").toURI().toString());
                    break;
                case 'M':
                    cellImage = new Image(getClass().getResource("magenta.png").toURI().toString());
                    break;
                default:
                    System.err.println("Check level file! Possible wrong letter");
                    System.exit(1);
            }
        } catch(Exception e) {
            System.err.println("Check image files!");
            System.exit(1);
        }
    }

    /**
     * constructor that creates a cell
     * @param colourCode - character that identifies a cell's colour
     */
    public Cell(char colourCode) {
        this.colourCode = colourCode;
        setCellImage(this.colourCode);
    }

    /**
     * method that gets a cell's colour code
     * @return colourCode
     */
    public char getColourCode() {
        return colourCode;
    }

    /**
     * method that gets a cell image
     * @return cellImage
     */
    public Image getCellImage() {
        return cellImage;
    }
}
