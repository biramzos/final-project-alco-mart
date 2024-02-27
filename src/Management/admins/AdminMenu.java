package Management.admins;

import Management.common.AlcoCategory;
import Management.common.Manager;
import Management.common.Menu;
import Management.common.Model;
import Management.products.AlcoMart;
import Management.products.AlcoMartManager;

import java.util.List;
import java.util.Scanner;

public class AdminMenu implements Menu {
    @Override
    public void launchMenu(Manager manager, int userId) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("[+] Admin menu:");
            System.out.println("[1] Show all alcohol products.");
            System.out.println("[2] Add a new beverage.");
            System.out.println("[3] Edit an existing beverage.");
            System.out.println("[4] Delete a beverage.");
            System.out.println("[5] Sign out.");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showAllProducts(((AlcoMartManager) manager));
                    break;
                case 2:
                    addAlcohol(((AlcoMartManager) manager), scanner);
                    break;
                case 3:
                    editAlcohol(((AlcoMartManager) manager), scanner);
                    break;
                case 4:
                    deleteAlcohol(((AlcoMartManager) manager), scanner);
                    break;
                case 5:
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

    private void addAlcohol(AlcoMartManager manager, Scanner scanner) {
        AlcoMart alcohol = new AlcoMart();
        System.out.println("[+] Adding a new beverage:");
        System.out.print("[name] ");
        alcohol.setName(scanner.next());
        System.out.println("[choose category] ");
        int i = 1;
        for (AlcoCategory category: AlcoCategory.values()) {
            System.out.println("[" + i + "] " + category.name());
            i++;
        }
        int category = scanner.nextInt();
        switch (category) {
            case 1:
                alcohol.setCategory(AlcoCategory.Alcohol);
                break;
            case 2:
                alcohol.setCategory(AlcoCategory.Whiskey);
                break;
            case 3:
                alcohol.setCategory(AlcoCategory.Wine);
                break;
            case 4:
                alcohol.setCategory(AlcoCategory.Tequila);
                break;
            case 5:
                alcohol.setCategory(AlcoCategory.Champagne);
                break;
        }

        System.out.print("[volume] ");
        alcohol.setVolume(scanner.nextDouble());
        System.out.print("[year] ");
        alcohol.setYear(scanner.nextInt());
        System.out.print("[price] ");
        alcohol.setPrice(scanner.nextDouble());
        System.out.print("[percent] ");
        alcohol.setPercent(scanner.nextInt());

        if(manager.insert(alcohol)) {
            System.out.println("Successfully inserted!");
        } else {
            throw new RuntimeException("Error inserting!");
        }
    }

    private void editAlcohol(AlcoMartManager manager, Scanner scanner) {
        List<Model> alcoMarts = manager.getList();
        System.out.println("[+] Editing a beverage:");
        System.out.println("[+] Choose beverage for editing:");
        for (Model alcohol: alcoMarts) {
            System.out.println(((AlcoMart) alcohol).toString());
        }
        System.out.println("[choose alcohol id] ");
        Model alcohol = manager.get(scanner.nextInt());
        System.out.println("[name] ");
        ((AlcoMart) alcohol).setName(scanner.next());
        System.out.print("[price] ");
        ((AlcoMart) alcohol).setPrice(scanner.nextDouble());

        if(manager.update(alcohol)) {
            System.out.println("Successfully updated!");
        } else {
            throw new RuntimeException("Error updated!");
        }
    }

    private void deleteAlcohol(AlcoMartManager manager, Scanner scanner) {
        List<Model> alcoMarts = manager.getList();
        System.out.println("[+] Deleting a beverage:");
        System.out.println("[+] Choose beverage for deleting:");
        for (Model alcohol: alcoMarts) {
            System.out.println(alcohol);
        }
        System.out.println("[choose alcohol id] ");
        int id = scanner.nextInt();

        if(manager.delete(id)) {
            System.out.println("Successfully deleted!");
        } else {
            throw new RuntimeException("Error deleted!");
        }
    }
}
