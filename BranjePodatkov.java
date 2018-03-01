
import java.util.*;

public class BranjePodatkov {

    public static int preberiInt() {		         
        boolean uspeh = false;
        int stevilo = 0;
        while (!uspeh) {
            try {
                Scanner scanner = new Scanner(System.in);
                stevilo = scanner.nextInt();
                uspeh = true;
            } catch (InputMismatchException e) {
                System.out.print("Napacen format stevila! Ponovitev vnosa: ");
            }
        }
        return stevilo;
    }

    public static double preberiDouble() {		         
        boolean uspeh = false;
        double stevilo = 0.0;
        while (!uspeh) {
            try {
                Scanner scanner = new Scanner(System.in);
                stevilo = scanner.nextDouble();
                uspeh = true;
            } catch (InputMismatchException e) {
                System.out.print("Napacen format stevila! Ponovitev vnosa: ");
            }
        }
        return stevilo;
    }

    public static String preberiString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
