import java.util.Scanner;
import java.util.Stack;

class Main {
    static Stack<String> stack = new Stack<>();

    public static void partition(int n) {
        partition(n, n, "");
    }

    public static void partition(int n, int max, String prefix) {
        if (n == 0) {
//            StdOut.println(prefix);
            stack.push(prefix);
            return;
        }

        for (int i = Math.min(max, n); i >= 1; i--) {
            partition(n - i, i, prefix + " " + i);
            System.out.println("partition(n-i=" + n + "-" + i + "=" + (n - i) + ", " + i + ", " + prefix + " " + i);
        }
    }
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());
        partition(number);
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
