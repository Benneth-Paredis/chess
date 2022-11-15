package main;

import pieces.Piece;
import pieces.King;
import pieces.Queen;
import pieces.Bishop;
import pieces.Pawn;
import pieces.Knight;
import pieces.Tower;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    public Color boardColorLightSquare = new Color(255, 255, 255);
    public Color boardColorDarkSquare = new Color(0, 125, 0);

    public int tileSize = 85;

    int rows = 8;
    int cols = 8;

    ArrayList<Piece> pieceList = new ArrayList<>();

    public Piece selectedPiece;

    Input input = new Input(this);

    public Board(){
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        addPieces();
    }

    public Piece getPiece(int col, int row){

        for(Piece piece : pieceList)
        {
            if(piece.col == col && piece.row == row)
            {
                return piece;
            }
        }

        return null;
    }

    public void makeMove(Move move){
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;

        move.piece.xPos = move.newCol * tileSize;
        move.piece.yPos = move.newRow * tileSize;

        capture(move);
    }

    public void capture(Move move){
        pieceList.remove(move.capture);
    }

    public boolean isValidMove(Move move){
        
        if(sameTeam(move.piece, move.capture)){
            return false;
        }
        return true;
    }

    public boolean sameTeam(Piece piece1, Piece piece2){
        if(piece1 == null || piece2 == null)
        {
            return false;
        }
        return piece1.isWhite == piece2.isWhite;
    }

    public void addPieces(){
        //<====== KING WHITE ======>//
        pieceList.add(new King(this, 4, 7, true));

        //<====== KING BLACK ======>//        

        pieceList.add(new King(this, 4, 0, false));

        //<====== QUEEN WHITE ======>//
        pieceList.add(new Queen(this, 3, 7, true));

        //<====== QUEEN BLACK ======>//        

        pieceList.add(new Queen(this, 3, 0, false));

        //<====== PAWN WHITE ======>//
        pieceList.add(new Pawn(this, 0, 6, true));
        pieceList.add(new Pawn(this, 1, 6, true));
        pieceList.add(new Pawn(this, 2, 6, true));
        pieceList.add(new Pawn(this, 3, 6, true));
        pieceList.add(new Pawn(this, 4, 6, true));
        pieceList.add(new Pawn(this, 5, 6, true));
        pieceList.add(new Pawn(this, 6, 6, true));
        pieceList.add(new Pawn(this, 7, 6, true));

        //<====== PAWN BLACK ======>//
        pieceList.add(new Pawn(this, 0, 1, false));
        pieceList.add(new Pawn(this, 1, 1, false));
        pieceList.add(new Pawn(this, 2, 1, false));
        pieceList.add(new Pawn(this, 3, 1, false));
        pieceList.add(new Pawn(this, 4, 1, false));
        pieceList.add(new Pawn(this, 5, 1, false));
        pieceList.add(new Pawn(this, 6, 1, false));
        pieceList.add(new Pawn(this, 7, 1, false));
        
        //<====== TOWER WHITE ======>//
        pieceList.add(new Tower(this, 0, 7, true));
        pieceList.add(new Tower(this, 7, 7, true));

         //<====== TOWER BLACK ======>//
         pieceList.add(new Tower(this, 0, 0, false));
         pieceList.add(new Tower(this, 7, 0, false));

        //<====== KNIGHT WHITE ======>//
        pieceList.add(new Knight(this, 1, 7, true));
        pieceList.add(new Knight(this, 6, 7, true));

        //<====== KNIGHT BLACK ======>//
        pieceList.add(new Knight(this, 1, 0, false));
        pieceList.add(new Knight(this, 6, 0, false));

        //<====== BISHOP WHITE ======>//
        pieceList.add(new Bishop(this, 2, 7, true));
        pieceList.add(new Bishop(this, 5, 7, true));

        //<====== BISHOP BLACK ======>//
        pieceList.add(new Bishop(this, 2, 0, false));
        pieceList.add(new Bishop(this, 5, 0, false));
        
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++){
                g2d.setColor((c+r) % 2 == 0 ? boardColorLightSquare : boardColorDarkSquare);
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        
        for(Piece piece : pieceList){
            piece.paint(g2d);
        }
    }
}
