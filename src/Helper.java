import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static void main(String[] args) {
        List<Pint2D> lista  = new ArrayList<Pint2D>();
        Pint2D head = new Pint2D(0, 0);

        lista.add(head);

        System.out.println(lista.get(0).getX());

        head.setLocation(1, 1);

        System.out.println(lista.get(0).getX());


        System.out.println("I need help");
    }
    
}
