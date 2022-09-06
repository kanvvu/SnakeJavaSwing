public class snakePart {
    
    private Pint2D location;
    private Pint2D destiny;

    public snakePart(Pint2D loc) {
        this.location = new Pint2D(loc.getX(), loc.getY());
        this.destiny = new Pint2D(0, 0);
    }

    public snakePart(Pint2D loc, Pint2D des) {
        this.location = new Pint2D(loc.getX(), loc.getY());
        this.destiny = new Pint2D(des.getX(), des.getY());
    }

    public void setLocation(int x, int y) {
        location = new Pint2D(x, y);
    }

    public void setLocation(Pint2D p) {
        location = new Pint2D(p.getX(), p.getY());
    }

    public void setDestiny(int x, int y) {
        destiny = new Pint2D(x, y);
    }

    public void setDestiny(Pint2D p) {
        destiny = new Pint2D(p.getX(), p.getY());
    }

    public Pint2D getLocation() {
        return location;
    }

    public Pint2D getDestiny() {
        return destiny;
    }

    public int getX() {
        return location.getX();
    }

    public int getY() {
        return location.getY();
    }

    public int getDesX() {
        return destiny.getX();
    }

    public int getDesY() {
        return destiny.getY();
    }

    





}
