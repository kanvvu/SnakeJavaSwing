import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {
    
    private Timer timer;
    private Character character;
    private Food food;
    private final int DELAY = 50;

    private GUI gui;
    private int offsetX;
    private int offsetY;

    private int width;
    private int height;

    private boolean isRunning;

    private int gridSize;

    public Board(int width, int height) {
        addKeyListener(new TAdapter());
        setBackground(new Color(0,200,0));
        setFocusable(true);

        this.width = width;
        this.height = height;

        gridSize = 30;

        this.offsetX = 0;
        this.offsetY = 60;

        this.isRunning = true;

        character = new Character();
        food = new Food();

        gui = new GUI(width,height);

        timer = new Timer(DELAY,this );
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
        
        Toolkit.getDefaultToolkit().sync();
    }

    private void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Color color1 = new Color(0,200,0);
        Color color2 = new Color(0,255,0);



        for(int i = 0; i<600/gridSize; i++) {
            // g.setColor(Color.white);
            // g.drawLine(i*gridSize, 0, i*gridSize, 600);
            // g.drawLine(0, i*gridSize, 600 , i*gridSize);

            for(int j=0; j<600/gridSize; j++) {
                if( i % 2 == 0) {
                    if(j% 2 != 0) 
                        g.setColor(color1);
                    else
                        g.setColor(color2);
                }
                else{
                    if(j% 2 == 0) 
                        g.setColor(color1);
                    else
                        g.setColor(color2);
                }
                g.fillRect(j*30+offsetX, i*30+offsetY, 30, 30);
            }
        }
        float thickness = 6;
        Stroke oldStroke = g2d.getStroke();

        g2d.setColor(color2);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawRect((int)thickness/2, (int)thickness/2, 20*30 - (int)thickness , 2*30-(int)thickness);
        g2d.setStroke(oldStroke);
        

        food.draw(g,offsetX,offsetY);
        character.draw(g,offsetX,offsetY);

        if(isRunning == true) gui.drawScore(g, character.getScore());
        if(isRunning == false) gui.drawEndScreen(g,character.getScore(),getTopScore());
    }

    public void actionPerformed(ActionEvent e) {
        if(isRunning) {
            if(character.getX() == food.getX() && character.getY() == food.getY()) {
            
                food = new Food();
                while ( character.collidesWithAll(food.getX(), food.getY()) )
                {
                    food = new Food();
                }
                character.eat();
            }
            
            character.move(); 
            
            if (character.getX() + 30 > this.width || character.getX() < 0 || character.getY() + 120 > height || character.getY() < 0 || character.collidesWithBody()) {
                isRunning = false;
                if(character.getScore() > getTopScore()) {
                    setTopScore(character.getScore());
                }
            }
        }

        repaint();
    }

    private int getTopScore() {
        int topScore = 0;
        try {
            File myObj = new File("topscore.txt");
            Scanner myReader = new Scanner(myObj);
            topScore = Integer.valueOf(myReader.nextLine());
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

        return topScore;
    }

    private void setTopScore(int i) {
        try {
            FileWriter myWriter = new FileWriter("topscore.txt");
            myWriter.write(String.valueOf(i));
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            character.kReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            character.kPressed(e);

            int key = e.getKeyCode();

            if(key == KeyEvent.VK_R) {
                if(isRunning == false) {
                    isRunning = true;
                    character.reset();

                }
            }
        }
    }
}