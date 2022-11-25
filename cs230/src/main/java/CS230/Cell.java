package CS230;

import javafx.scene.image.Image;

public class Cell {
    private char colourCode;

    private Image cellImage;

    private Image setCellImage(char colourCode) {
        try {
            switch(colourCode) {
                case 'R':
                    cellImage = new Image(getClass().getResource("CS230/red.png").toURI().toString());
                    break;
                case 'G':
                    cellImage = new Image(getClass().getResource("CS230/green.png").toURI().toString());
                    break;
                case 'B':
                    cellImage = new Image(getClass().getResource("CS230/blue.png").toURI().toString());
                    break;
                case 'Y':
                    cellImage = new Image(getClass().getResource("CS230/yellow.png").toURI().toString());
                    break;
                case 'C':
                    cellImage = new Image(getClass().getResource("CS230/cyan.png").toURI().toString());
                    break;
                case 'M':
                    cellImage = new Image(getClass().getResource("CS230/magenta.png").toURI().toString());
                    break;
                default:
                    System.err.println("Check level file! Possible wrong letter");
                    System.exit(1);
            }
        } catch(Exception e) {
            System.err.println("Check image files!");
            System.exit(1);
        }

        return cellImage;
    }

    public Cell(char colourCode) {
        this.colourCode = colourCode;
        this.cellImage = setCellImage(this.colourCode);
    }

    public char getColourCode() {
        return colourCode;
    }

    public void setColourCode(char colourCode) {
        this.colourCode = colourCode;
    }

    public Image getCellImage() {
        return cellImage;
    }
}
