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

    public InformationPanel informationPanel;

    public int tileSize = 85;

    int cols = 8;
    int rows = 8;

    int boardWidth = cols * tileSize;
    int boardHeight = rows * tileSize;

    //turn white: 0, turn black: 1
    int turn = 0;

    //Scores players collected from capturing pieces
    public int scoreWhite = 0;
    public int scoreBlack = 0;

    ArrayList<Piece> pieceList = new ArrayList<>();

    public Piece selectedPiece;

    public King kingWhite = new King(this, 4, 7, true);
    public King kingBlack = new King(this, 4, 0, false);

    Input input = new Input(this);

    public Board(int x, int y){
        this.setBounds(x, y, boardWidth, boardHeight);
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        addPieces();
    }

    //Returns piece on a specific tile
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

        move.piece.isFirstMove = false;

        capture(move);

        if(move.piece instanceof Pawn && move.piece.row == 0 || move.piece.row == 7){
            promotePiece(move);
        }
        informationPanel.updatePanel(scoreWhite, scoreBlack);
    }

    //Checks if it is the piece's turn
    public boolean isTurn(Move move){
        if(move.piece.isWhite && turn == 0){
            return true;
        }
        if((!move.piece.isWhite) && turn == 1){
            return true;
        }
        return false;
    }

    //Looks if it can capture a piece and if so removes it from the piecelist
    public void capture(Move move){
        if(move.capture != null)
        {
            if(move.capture.isWhite)
            {
                scoreBlack += move.capture.pieceValue;
            }
            else
            {
                scoreWhite += move.capture.pieceValue;
            }
        }
        pieceList.remove(move.capture);
    }

    //Checks if the move is a valid move
    public boolean isValidMove(Move move){
        //checks if its the piece's turn
        if(isTurn(move)){
            //checks if move is on another piece of the same color
            if(sameTeam(move.piece, move.capture)){
                return false;
            }
            //checks if the piece can move in a certain manner according to the piece rule
            if(!move.piece.isValidMovement(move.newCol, move.newRow)){
                return false;
            }
            //check if the piece collides with another
            if(move.piece.moveCollidesPiece(move.newCol, move.newRow)){
                return false;
            }
            return true;
        }
        return false;
    }

    //checks if two piece are on the same team
    public boolean sameTeam(Piece piece1, Piece piece2){
        if(piece1 == null || piece2 == null)
        {
            return false;
        }
        return piece1.isWhite == piece2.isWhite;
    }

    //Promotes piece to a queen
    public void promotePiece(Move move){
        boolean isWhite = move.piece.isWhite;
        //remove current piece
        pieceList.remove(move.piece);
        //add queen to tile
        pieceList.add(new Queen(this, move.newCol, move.newRow, isWhite));
    }

    //checks if the king can be captured
    public boolean check(King king){
        Move move;
        //Iterates through all chess pieces in pieceList and checks if they can capture the king
        for(Piece piece : pieceList){
            move = new Move(this, piece, king.col, king.row);
            //Same as isValidMove function but without the isTurn function
            if(!sameTeam(piece, king) && !(piece instanceof King) && piece.isValidMovement(move.newCol, move.newRow)
            && !piece.moveCollidesPiece(move.newCol, move.newRow))
            {
                return true;
            }
        }
        return false;
    }

    public void addPieces(){
        //<====== KING WHITE ======>//
        pieceList.add(kingWhite);

        //<====== KING BLACK ======>//        

        pieceList.add(kingBlack);

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
        


        //Highlight places where the selected piece can go
        if(selectedPiece != null)
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++){
                
                if(isValidMove(new Move(this, selectedPiece, c, r))){
                    g2d.setColor(new Color(0, 0, 0, 50));
                    g2d.fillOval(c*tileSize + tileSize/4, r*tileSize + tileSize/4, tileSize/2, tileSize/2);
                }

            }

        for(Piece piece : pieceList){
            piece.paint(g2d);
        }
    }
}
