package ex3.entity;

import ex3.run.MenuCategory;

import java.util.Scanner;

public class Category implements IBaseOject{
    private int id;
    private String name;
    private boolean status;

    public Category() {
    }

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.name = inputCategoryName(scanner);
        this.status = inputCategoryStatus(scanner);
    }

    @Override
    public void displayData() {
        System.out.printf(
                "[ ID: %d | Name: %s | Status: %s ]\n",
                this.id,
                this.name,
                this.status ? "Active" : "InActive"
        );
    }

    public String inputCategoryName(Scanner scanner) {
        System.out.println("Nhập tên danh mục sách: ");
        do {
            String categoryName = scanner.nextLine();
            if (categoryName.length() >= 6 && categoryName.length() <= 30) {
                boolean isExist = false;
                for (int i = 0; i < MenuCategory.indexCategory; i++) {
                    if (MenuCategory.categories[i].getName().equals(categoryName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên danh mục đã tồn tại");
                } else {
                    return categoryName;
                }
            } else {
                System.err.println("Tên danh mục phải từ 6 -> 30 ký tự");
            }
        } while (true);
    }

    public boolean inputCategoryStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái danh mục (true hoặc false): ");
        do {
            String status = scanner.nextLine().toLowerCase();
            if (status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Chỉ nhận 2 giá trị true hoặc false");
            }
        } while (true);
    }

}
