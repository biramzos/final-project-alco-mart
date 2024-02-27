package Management.clients;

import Management.common.Manager;
import Management.common.Menu;
import Management.common.Model;
import Management.common.bills.Bill;
import Management.products.AlcoMart;
import Management.products.AlcoMartManager;

import java.util.List;
import java.util.Scanner;

public class ClientMenu implements Menu {


    @Override
    public void launchMenu(Manager manager, int userId) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("[+] Client Menu:");
            System.out.println("[1] Show all alcohol products.");
            System.out.println("[2] Buy alcohol product.");
            System.out.println("[3] History of bills.");
            System.out.println("[4] Sign out.");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showAllProducts(((AlcoMartManager) manager));
                    break;
                case 2:
                    buyAlcoholProducts(((AlcoMartManager) manager), scanner, userId);
                    break;
                case 3:
                    historyOfBills(((AlcoMartManager) manager), userId);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void showAllProducts(AlcoMartManager manager) {
        List<Model> alcohols = manager.getList();
        System.out.println("[All alcohol products]");
        for (Model alcohol: alcohols) {
            System.out.println(((AlcoMart) alcohol).toString());
        }
        System.out.println();
    }

    private void buyAlcoholProducts(AlcoMartManager manager, Scanner scanner, int userId) {
        List<Model> alcohols = manager.getList();
        System.out.println("[All alcohol products]");
        for (Model alcohol: alcohols) {
            System.out.println(((AlcoMart) alcohol).toString());
        }
        System.out.println("[choose alcohol product id]");
        if (manager.billInsert(new Bill(scanner.nextInt(), userId))) {
            System.out.println("Successfully bought!");
        } else {
            throw new RuntimeException("Error!");
        }
    }

    private void historyOfBills(AlcoMartManager manager, int userId) {
        List<Model> alcohols = manager.getHistoryProducts(userId);
        System.out.println("[All alcohol products]");
        for (Model alcohol: alcohols) {
            System.out.println(((AlcoMart) alcohol).toString());
        }
        System.out.println();
    }
}
