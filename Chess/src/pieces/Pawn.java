package pieces;

import java.awt.image.BufferedImage;
import main.Board;

public class Pawn extends Piece{



    public Pawn(Board board, int col, int row, boolean isWhite){

        super(board);

        this.col = col;
        this.row = row;

        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;

        this.isWhite = isWhite;

        this.name = "pawn";

        this.sprite = sheet.getSubimage(5 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
    }
}
