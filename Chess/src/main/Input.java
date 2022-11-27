package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import pieces.Piece;

public class Input extends MouseAdapter
{

    Board board;

    public Input(Board board){
        this.board = board;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(board.selectedPiece != null){
            board.selectedPiece.xPos = e.getX() - board.tileSize / 2;
            board.selectedPiece.yPos = e.getY() - board.tileSize / 2;
            
            board.repaint();
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        Piece pieceXY = board.getPiece(col, row);
        if(pieceXY != null)
        {
            board.selectedPiece = pieceXY;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        if(board.selectedPiece != null)
        {
            Move move = new Move(board, board.selectedPiece, col, row);
            int oldCol = move.oldCol;
            int oldRow = move.oldRow;

            if(board.isValidMove(move))
            {
                board.makeMove(move);

                if(board.turn == 0)
                {
                    if(board.check(board.kingWhite))
                    {
                        board.makeMove(new Move(board, board.selectedPiece, oldCol, oldRow));
                        if(move.capture != null){
                            board.pieceList.add(move.capture);
                            board.scoreWhite -= move.capture.pieceValue;
                        }
                            
                        board.turn = (board.turn + 1) % 2;
                    }
                }
                if(board.turn == 1)
                {
                    if(board.check(board.kingBlack))
                    {
                        board.makeMove(new Move(board, board.selectedPiece, oldCol, oldRow));
                        if(move.capture != null){
                            board.pieceList.add(move.capture);
                            board.scoreBlack -= move.capture.pieceValue;
                        }
                        board.turn = (board.turn + 1) % 2;
                    }
                }

                board.turn = (board.turn + 1) % 2;
            }
            else 
            {
                    board.selectedPiece.xPos = board.selectedPiece.col * board.tileSize;
                    board.selectedPiece.yPos = board.selectedPiece.row * board.tileSize;
            }
        } 
        board.selectedPiece = null;
        board.informationPanel.updatePanel(board.scoreWhite, board.scoreBlack);
        board.repaint();
    }
}
