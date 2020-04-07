
/**
 *
 * @author Le Duc Hoa
 */
public class Validation {

    public static int checkInputLimit(int min, int max) {
        while (true) {
            try {
                int number = Integer.parseInt(Main.scanner.nextLine().trim());
                if (number < min || number > max) {
                    throw new Exception();
                }
                return number;
            } catch (Exception e) {
                System.err.println("Limit input [ " + min + " ," + max + " ]");
                System.out.print("Your choice, please: ");
            }
        }
    }

    public static String checkEmptyInputString(String string) {
        while (true) {
            try {
                String value = Main.scanner.nextLine();
                if (value.isEmpty()) {
                    throw new Exception();
                }
                return value;
            } catch (Exception e) {
                System.out.println("Can not except empty value");
                System.out.print("Enter " + string + " again: ");
            }

        }
    }

    public static double checkEmptyDouble(String string) {
        while (true) {
            try {
                double value = Double.parseDouble(Main.scanner.nextLine().trim());
                String check = "";
                if (check.equals(value)) {
                    throw new Exception();
                }
                return value;
            } catch (Exception e) {
                System.out.println("Can not except empty value or character!");
                System.out.print("Enter " + string + " again: ");
            }

        }

    }
       public static int checkEmptyInt(String string) {
        while (true) {
            try {
                int value = Integer.parseInt(Main.scanner.nextLine().trim());
                String check = "";
                if (check.equals(value)) {
                    throw new Exception();
                }
                return value;
            } catch (Exception e) {
                System.out.println("Can not except empty value or character!");
                System.out.print("Enter " + string + " again: ");
            }

        }

    }

}
