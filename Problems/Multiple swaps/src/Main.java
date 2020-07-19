import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] elements = scanner.nextLine().split(" ");
        int swaps = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < swaps; i++) {
            int num1 = scanner.nextInt();
            int num2 = scanner.nextInt();
            swap(elements, num1, num2);
        }
        for (String element : elements) {

            System.out.print(element + " ");
        }
    }

    private static void swap(String[] elements, int num1, int num2) {
        String temp = elements[num1];
        elements[num1] = elements[num2];
        elements[num2] = temp;
    }
}