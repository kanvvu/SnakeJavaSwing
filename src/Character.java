import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Character {

    private int bodyParts;

    private int w;
    private int h;
    private char dir;
    private int moveSpeed;
    private int destinyMove;

    private boolean click;
    private int helpINT;
    private int helpINT2;
    private Pint2D destiny;
    private List<Pint2D> destinyList; 

    //private MovDir movDir;
    private char movDir;
    private char lastDir;
    private List<snakePart> snakeBody;
    private Pint2D snakeHead;

    private boolean faster;

    private int score;

    public Character() {
        this.snakeHead = new Pint2D(5*30, 60);

        bodyParts = 5;

        this.w = 30;
        this.h = 30;

        this.moveSpeed = 10;
        this.destinyMove = 30;
        this.dir = 'e';

        this.score = 0;

        this.click = false;
  
        this.helpINT = 0;
        this.helpINT2 = 0;

        this.movDir = 'n';
        this.lastDir = movDir;
        this.destiny = new Pint2D(0,0);
        
        this.snakeBody = new ArrayList<snakePart>();
        this.destinyList = new ArrayList<Pint2D>();
        
        //this.snakeBody.add(new snakePart(new Pint2D(4*30, 60)));
        for(int i = 0; i<bodyParts;i++){
            snakeBody.add(new snakePart(new Pint2D(i*30+30, 60)));
            destinyList.add(new Pint2D(i*30+30, 60));
        }

        this.faster = true;
    
    }

    public int getX() {
        return snakeHead.getX();
    }

    public int getY() {
        return snakeHead.getY();
    }

    public void draw(Graphics g, int x ,int y) {

        Color color1 = new Color(0,50,0);
        Color color2 = new Color(0,100,0);
        g.setColor(Color.white);
        for(int i = 0; i<snakeBody.size(); i++) {

            g.setColor(color1);
            g.fillRect(snakeBody.get(i).getX() + x, snakeBody.get(i).getY() + y, w, h);
        }

    }

    public void move() {

            switch(dir) {
                case 'u':
                    changeMovDir('u');
                    break;

                case 'd':
                    changeMovDir('d');  
                    break;
                
                case 'l':
                    changeMovDir('l');
                    break;
                
                case 'r':
                    changeMovDir('r');
                    break;
                default:
                    break;
            }

        moveSmooth();

        if (snakeBody.size() > bodyParts) {
            snakeBody.remove(0);
        }
        
        click = false;
    }

    public void changeMovDir(char mD) {
        if (this.movDir == 'n' || mD == 'n') {
            this.movDir = mD;

            int x = snakeHead.getX();
            int y = snakeHead.getY();
        
            switch(mD) {
                case 'u':
                if (lastDir == 'd'){
                    movDir = 'd';
                }
                break;
                case 'd':
                if (lastDir == 'u'){
                    movDir = 'u';
                }
                break;
                case 'r':
                if (lastDir == 'l'){
                    movDir = 'l';
                }
                break;
                case 'l':
                if (lastDir == 'r'){
                    movDir = 'r';
                }
                break;
            }

            lastDir = movDir;

            switch(this.movDir) {
                case 'u':
                    destiny.setLocation(x, y - destinyMove);
                break;
                case 'd':
                    destiny.setLocation(x, y + destinyMove);
                break;
                case 'l':
                    destiny.setLocation(x - destinyMove, y);
                break;
                case 'r':
                    destiny.setLocation(x + destinyMove, y);
                break;
                default:
                break;
            }
            

            destinyList.add(new Pint2D(destiny.getX(), destiny.getY()));

            if(destinyList.size() > bodyParts) {
                destinyList.remove(0);
            }

        }
    }



    public void moveSmooth() {
        if (this.movDir != 'n') {
            int x = snakeHead.getX();
            int y = snakeHead.getY();
            if(x != destiny.getX() || y != destiny.getY()) {
                for(int i = snakeBody.size()-1; i >= 0; i--) {
                    int j = i;
                    if(destinyList.size() != snakeBody.size()) {
                        j = i + 1;
                    }
                    int xi = snakeBody.get(i).getX();
                    int yi = snakeBody.get(i).getY(); 

                    int diffX = xi - destinyList.get(j).getX();
                    int diffY = yi - destinyList.get(j).getY();
                    int mov = moveSpeed;

                    if(diffX > 0)
                    xi -= mov;
                    if(diffX < 0)
                    xi += mov;

                    if(diffY > 0)
                    yi -= mov;
                    if(diffY < 0)
                    yi += mov;

                    snakeBody.get(i).setLocation(xi, yi);
                }

                snakeHead.setLocation(snakeBody.get(snakeBody.size()-1).getX(), snakeBody.get(snakeBody.size()-1).getY());
                
            } 
            else {
                this.movDir = 'n';

                snakeBody.clear();
                for(int i = 0; i<destinyList.size(); i++) {
                    snakeBody.add(new snakePart(new Pint2D(destinyList.get(i).getX(), destinyList.get(i).getY())));
                }

                if(faster) {
                    moveSpeed = 10;
                }
                else {
                    moveSpeed = 1;
                }
   

            }
        }

    }
    public boolean collidesWithBody() {
        for(int i = 0; i<snakeBody.size() - 1; i++) {
            if(snakeBody.get(snakeBody.size()-1).getX() == snakeBody.get(i).getX() && snakeBody.get(snakeBody.size()-1).getY() == snakeBody.get(i).getY()){
                return true;
            }
        }

        return false;
    }

    public void reset() {
        score = 0;
        bodyParts = 5;
        movDir = 'n';
        lastDir = movDir;
        dir = 'e';


        this.snakeHead = new Pint2D(5*30, 60);
        snakeBody.clear();
        destinyList.clear();
        for(int i = 0; i<bodyParts;i++){
            snakeBody.add(new snakePart(new Pint2D(i*30+30, 60)));
            destinyList.add(new Pint2D(i*30+30, 60));
        }

    }


    public void kPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dir = 'l';
        }

        if (key == KeyEvent.VK_D) {
            dir = 'r';
        }

        if (key == KeyEvent.VK_W) {
            dir = 'u';
        }

        if (key == KeyEvent.VK_S) {
            dir = 'd';
        }

        // if (key == KeyEvent.VK_R) {
        //     reset();
        // }

        if (key == KeyEvent.VK_SPACE) {
            faster = !faster;
        }

        helpINT += 1;

        if(helpINT >= 1){
            helpINT2 += 1;
        } else {
            helpINT2 = 0;
        }

        if(helpINT2 == 1) {
            click = true;
        } else {
            click = false;
        }

        
    }

    public void kReleased(KeyEvent e) {
        
        int key = e.getKeyCode();
        //dir = 'e';

        click = false;
        helpINT = 0;
        helpINT2 = 0;
    }

    public void eat() {
        bodyParts++;
        score += 1;
    }

    public int getScore() {
        return this.score;
    }

    public boolean collidesWithAll(int x, int y) {

        for(int i = 0; i<snakeBody.size(); i++) {
            if(snakeBody.get(i).getX() == x && snakeBody.get(i).getY() == y) return true;
        }

        return false;
    }
    
}
