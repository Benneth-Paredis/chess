package main;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)(screenSize.getWidth());
        int screenHeight = (int)(screenSize.getHeight());

        //Size of the chessboard
        Dimension boardSize = new Dimension(8*85, 8*85);

        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(null);
        frame.setMinimumSize(screenSize);
        frame.setLocationRelativeTo(null);

        InformationPanel informationPanel = new InformationPanel((int)((screenWidth / 2) - (boardSize.getWidth() / 2)), (int)(screenHeight*0.05), (int)(boardSize.getWidth()));
        frame.add(informationPanel);

        Board board = new Board((screenWidth / 2) - (boardSize.width / 2), (screenHeight / 2) - (boardSize.height / 2));
        frame.add(board);

        frame.setTitle("Chess");
        frame.setVisible(true);

    }
}
