package pieces;

import main.Board;
import java.awt.image.BufferedImage;

public class Tower extends Piece{
    
    public Tower(Board board, int col, int row, boolean isWhite){

        super(board);

        this.col = col;
        this.row = row;

        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.isWhite = isWhite;

        this.name = "tower";

        this.pieceValue = 5;

        this.sprite = sheet.getSubimage(4 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int col, int row){
        //Horizontal or Vertical movement
        return(col == this.col || row == this.row);
    }

    public boolean moveCollidesPiece(int col, int row)
    {
    //check collision left
    if(this.col > col){
        for(int c = this.col - 1; c > col; c--)
            if(board.getPiece(c, this.row) != null)
                return true;
    }
    //check collision right
    if(this.col < col){
        for(int c = this.col + 1; c < col; c++)
            if(board.getPiece(c, this.row) != null)
                return true;
    }

    //check collision up
    if(this.row > row){
        for(int r = this.row - 1; r > row; r--)
            if(board.getPiece(this.col, r) != null)
                return true;
    }

    //check collision down
    if(this.row < row){
        for(int r = this.row + 1; r < row; r++)
            if(board.getPiece(this.col, r ) != null)
                return true;
    }
    return false;
    }
}
