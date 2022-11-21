package main;

import pieces.Piece;

public class Move {
    //Old position
    int oldCol;
    int oldRow;

    //New position
    int newCol;
    int newRow;

    //Current piece
    Piece piece;

    //Piece to be captured
    Piece capture;

    public Move(Board board, Piece piece, int newCol, int newRow)
    {
            this.oldCol = piece.col;
            this.oldRow = piece.row;
            
            this.newCol = newCol;
            this.newRow = newRow;

            this.piece = piece;
            this.capture = board.getPiece(newCol, newRow);
    }
}
