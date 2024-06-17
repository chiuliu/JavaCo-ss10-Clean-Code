package ex3.run;

import ex3.entity.Category;

import java.util.Scanner;

public class MenuCategory {
    public static Category[] categories = new Category[0];
    public static int indexCategory = 0;

    public static void menuCategory(Scanner scanner) {
        do {
            System.out.println("**************** MENU ****************");
            System.out.println("1. Thêm mới danh mục");
            System.out.println("2. Hiển thị danh sách theo tên danh mục a-z");
            System.out.println("3. Thống kê danh mục và số sách trong mỗi danh mục");
            System.out.println("4. Cập nhật danh mục");
            System.out.println("5. Xoá danh mục");
            System.out.println("6. Thoát");
            System.out.println("**************************************");
            System.out.println("Lựa chọn đê: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addNewCategory(scanner);
                    break;
                case 2:
                    showListCategory();
                    break;
                case 3:

                    break;
                case 4:
                    updateCategory(scanner);
                    break;
                case 5:
                    deleteCategoryById(scanner);
                    break;
                case 6:
                    MainMenu.menuMain(scanner);
                    break;
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 3");
            }
        } while (true);
    }

    public static void showListCategory() {
        if (categories != null) {
            for (int i = 0; i < indexCategory; i++) {
                categories[i].displayData();
            }
        }
    }

    public static void addNewCategory(Scanner scanner) {
        System.out.println("Nhập vào số lượng muốn thêm");
        int n = Integer.parseInt(scanner.nextLine());

        Category[] newCategories = new Category[categories.length + n];

        if (indexCategory == 0) {
            for (int i = categories.length; i < n; i++) {
                newCategories[i] = new Category();
                newCategories[i].setId(getNewCategoryId(newCategories));
                newCategories[i].inputData(scanner);
                categories = newCategories;
                indexCategory++;
            }
        } else {
            for (int i = 0; i < categories.length; i++) {
                newCategories[i] = categories[i];
            }
            for (int i = 0; i < n; i++) {
                newCategories[indexCategory] = new Category();
                newCategories[indexCategory].setId(getNewCategoryId(newCategories));
                newCategories[indexCategory].inputData(scanner);
                categories = newCategories;
                indexCategory++;
            }
        }
    }

    public static int getNewCategoryId(Category[] categories) {
        int maxId = 0;
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] != null && categories[i].getId() > maxId) {
                maxId = categories[i].getId();
            }
        }
        return maxId + 1;
    }

    public static void updateCategory(Scanner scanner) {
        System.out.println("Enter the name of the Category");
        int CategoryId = Integer.parseInt(scanner.nextLine());
        int indexEdit = getIndexById(CategoryId);
        if (indexEdit != -1) {
            boolean isExit = true;
            categories[indexEdit].displayData();
            do {
                System.out.println("1. Update Category Name");
                System.out.println("2. Update Category Status");
                System.out.println("3. Exit");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        categories[indexEdit].setName(categories[indexEdit].inputCategoryName(scanner));
                        break;
                    case 2:
                        categories[indexEdit].setStatus(categories[indexEdit].inputCategoryStatus(scanner));
                        break;
                    case 3:
                        menuCategory(scanner);
                        break;
                    default:
                        isExit = false;
                }
            } while (isExit);

        } else {
            System.out.println("There is no singer with id to edit" + CategoryId);
        }
    }

    public static void deleteCategoryById(Scanner sc) {
        System.out.println("Enter Category id to delete:");
        int CategoryId = Integer.parseInt(sc.nextLine());
        int indexDelete = getIndexById(CategoryId);
        if (indexDelete != -1) {
            boolean isExit = false;
            for (int i = 0; i < MenuBook.indexBooks; i++){
                if (MenuBook.books[i].getCategory().getId() == CategoryId){
                    isExit = true;
                }
            }
            if (isExit) {
                System.err.println("This Category has many book so cant delete");
            }else {
                for (int i = indexDelete; i < indexCategory-1; i++) {
                    categories[i] = categories[i + 1];
                }
                indexCategory--;
            }

        } else {
            System.out.println("There is no Category with id to delete" + CategoryId);
        }
    }

    public static int getIndexById(int CategoryId) {
        for (int i = 0; i < indexCategory; i++) {
            if (categories[i].getId() == CategoryId) {
                return i;
            }
        }
        return -1;
    }
}
