package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class InformationPanel extends JPanel
{
    public JLabel score_white_label = new JLabel("Score: " + 0);
    public JLabel score_black_label = new JLabel("Score: " + 0);

    public Color primaryColor = new Color(0, 0, 0);
    public Color secondaryColor = new Color(0, 0, 0);
    public Color backgroundColor = new Color(0, 125, 0);

    public Board board;

    ImageIcon input_king_white = new ImageIcon(getClass().getResource("/res/king_white.png"));
    Image king_white_img = input_king_white.getImage();
    Image king_white_img_scaled = king_white_img.getScaledInstance(96, 96, Image.SCALE_SMOOTH);
    ImageIcon king_white = new ImageIcon(king_white_img_scaled);

    ImageIcon input_king_black= new ImageIcon(getClass().getResource("/res/king_black.png"));
    Image king_black_img = input_king_black.getImage();
    Image king_black_img_scaled = king_black_img.getScaledInstance(96, 96, Image.SCALE_SMOOTH);
    ImageIcon king_black = new ImageIcon(king_black_img_scaled);
    

    public int infoPanelHeight = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.1);

    public InformationPanel(Board board, int x, int y, int boardWidth)
    {
        this.board = board;

        this.setBounds(x, y, boardWidth, infoPanelHeight);
        this.setBackground(backgroundColor);

        this.add(new JLabel(king_white));
        this.add(score_white_label);
        this.add(new JLabel(king_black));
        this.add(score_black_label);
    }

    public void updatePanel(int scoreWhite, int scoreBlack)
    {
        score_white_label.setText("Score: " + scoreWhite);
        score_black_label.setText("Score: " + scoreBlack);
    }
}
