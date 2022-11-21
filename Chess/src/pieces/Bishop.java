package pieces;

import java.awt.image.BufferedImage;
import main.Board;

public class Bishop extends Piece{
    
    public Bishop(Board board, int col, int row, boolean isWhite){

        super(board);

        this.col = col;
        this.row = row;

        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.isWhite = isWhite;

        this.name = "bishop";

        this.sprite = sheet.getSubimage(2 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int col, int row){
        //Diagonal
        return(Math.abs(col - this.col) == Math.abs(row - this.row));
    }
}
