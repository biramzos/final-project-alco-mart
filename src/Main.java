import Management.common.AuthManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("[+] Welcome!");
        System.out.println("[+] Choose your option:");
        System.out.println("[1] Sign in.");
        System.out.println("[2] Sign up.");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch(choice) {
            case 1:
                AuthManager.signIn(scanner);
                break;
            case 2:
                AuthManager.signUp(scanner);
                break;
        }
    }

}