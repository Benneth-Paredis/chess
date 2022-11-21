package pieces;

import main.Board;
import java.awt.image.BufferedImage;
public class Queen extends Piece{
    
    public Queen(Board board, int col, int row, boolean isWhite){

        super(board);

        this.col = col;
        this.row = row;

        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.isWhite = isWhite;

        this.name = "tower";

        this.sprite = sheet.getSubimage(sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int col, int row){
        return(col == this.col || row == this.row || Math.abs(col - this.col) == Math.abs(row - this.row));
    }
}
