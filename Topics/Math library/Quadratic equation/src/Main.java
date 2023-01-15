import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();

        double root1, root2;

        double d = Math.sqrt(Math.pow(b, 2) - (4 * a * c));
        root1 = (-b + d) / (2 * a);
        root2 = (-b - d) / (2 * a);

        System.out.printf("%f %f", Math.min(root1, root2), Math.max(root1, root2));

    }
}
