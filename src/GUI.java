import java.awt.*;


public class GUI {

    private int screenWidht;
    private int screenHeight;

    public GUI(int width, int height) {
        this.screenHeight = height;
        this.screenWidht = width;
    }

    
    public void drawScore(Graphics g, int score) {
        
        String text = "Score: " + score;
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));

        int x = screenWidht/2 - g.getFontMetrics().stringWidth(text)/2 ;
        int y = 10;
        
        g.setColor(new Color(20,20,20, 100));
        g.fillRect(x - 5, y, g.getFontMetrics().stringWidth(text) + 10, g.getFont().getSize() + 9 );

        g.setColor(Color.white);
        g.drawString(text, x, g.getFont().getSize() + y);

    }

    public void drawEndScreen(Graphics g, int score, int topScore) {
        
        String youLostStr = "YOU LOST";
        String topScoreStr = "TOP SCORE";
        String yourScore = "YOUR SCORE";
        String clickToStr = "CLICK 'R' TO RESTART GAME";
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));

        int x = screenWidht/2 - g.getFontMetrics().stringWidth(youLostStr)/2 ;
        int y = screenHeight/2 - 200;
        int rectHeight = 200;
        
        g.setColor(new Color(0,150,0, 240));
        g.fillRect(x - 5, y, g.getFontMetrics().stringWidth(youLostStr) + 10, g.getFont().getSize() + 9 + rectHeight );

        g.setColor(Color.white);
        g.drawString(youLostStr, x, g.getFont().getSize() + y);

        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        x = screenWidht/2 - g.getFontMetrics().stringWidth(topScoreStr)/2;
        g.drawString(topScoreStr, x, g.getFont().getSize() + y + 70);

        x = screenWidht/2 - g.getFontMetrics().stringWidth(String.valueOf(topScore))/2;
        g.drawString(String.valueOf(topScore), x, g.getFont().getSize() + y + 100);

        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));
        x = screenWidht/2 - g.getFontMetrics().stringWidth(yourScore)/2 ;
        g.drawString(yourScore, x, g.getFont().getSize() + y + 150);

        x = screenWidht/2 - g.getFontMetrics().stringWidth(String.valueOf(score))/2;
        g.drawString(String.valueOf(score), x, g.getFont().getSize() + y + 180);

        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        x = screenWidht/2 - g.getFontMetrics().stringWidth(clickToStr)/2;
        g.drawString(clickToStr, x, g.getFont().getSize() + y + 230);

    }

}
