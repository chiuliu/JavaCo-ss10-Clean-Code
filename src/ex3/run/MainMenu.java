package ex3.run;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menuMain(scanner);
    }

    public static void menuMain(Scanner scanner) {
        do {
            System.out.println("**************** MENU ****************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("**************************************");
            System.out.println("Lựa chọn đê: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    MenuCategory.menuCategory(scanner);
                    break;
                case 2:
                    MenuBook.menuBook(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 3");
            }
        } while (true);
    }


    public static int getNewProductId() {
        return 0;
    }
}
