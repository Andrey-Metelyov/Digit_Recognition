import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int units = Integer.parseInt(scanner.nextLine());
        if (units < 1) {
            System.out.println("no army");
        } else if (units < 20) {
            System.out.println("pack");
        } else if (units < 250) {
            System.out.println("throng");
        } else if (units < 1000) {
            System.out.println("zounds");
        } else {
            System.out.println("legion");
        }
    }
}