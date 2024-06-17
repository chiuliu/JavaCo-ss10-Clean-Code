package ex3.entity;

import ex3.run.MenuBook;
import ex3.run.MenuCategory;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Book implements IBaseOject{
    private String id;
    private String title;
    private String author;
    private int year;
    private String description;
    private Category category;

    public Book() {
    }

    public Book(String id, String title, String author, int year, String description, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.description = description;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.id = inputProductId(scanner);
        this.title = inputProductTittle(scanner);
        this.author = inputAuthor(scanner);
        this.year = inputYear(scanner);
        this.description = inputDescription(scanner);
        this.category = inputCategory(scanner);
    }

    @Override
    public void displayData() {

    }

    public String inputProductId(Scanner scanner) {
        System.out.println("Nhập vào mã quyển sách: ");
        String regex = "B\\d{3}";
        do {
            String productId = scanner.nextLine();
            if (Pattern.matches(regex, productId)) {
                boolean isExist = false;
                for (int i = 0; i < MenuBook.indexBooks; i++) {
                    if (MenuBook.books[i].getId().equals(productId)) {
                        isExist = true;
                    }
                }
                if (isExist) {
                    System.err.println("Đã tồn tại mã sách");
                } else {
                    return productId;
                }
            } else {
                System.err.println("id có 4 ký tự và bắt đầu bằng chữ B");
            }
        } while (true);
    }

    public String inputProductTittle(Scanner scanner) {
        System.out.println("Nhập vào tiêu đề quyển sách: ");
        String regex = "\\w{6,50}";
        do {
            String title = scanner.nextLine();
            if (Pattern.matches(regex, title)) {
                boolean isExist = false;
                for (int i = 0; i < MenuBook.indexBooks; i++) {
                    if (MenuBook.books[i].getTitle().equals(title)) {
                        isExist = true;
                    }
                }
                if (isExist) {
                    System.err.println("Tiêu đề không được trùng");
                } else {
                    return title;
                }
            } else {
                System.err.println("Tiêu đề phải có từ 6 -> 50 ký tự");
            }
        } while (true);
    }

    public String updateProductTittle(Scanner scanner, int indexUpdate) {
        System.out.println("Nhập vào tiêu đề quyển sách: ");
        String regex = "\\w{6,50}";
        do {
            String title = scanner.nextLine();
            if (Pattern.matches(regex, title)) {
                boolean isExist = false;
                for (int i = 0; i < MenuBook.indexBooks; i++) {
                    if(i!=indexUpdate){
                        if (MenuBook.books[i].getTitle().equals(title)) {
                            isExist = true;
                        }
                    }
                }
                if (isExist) {
                    System.err.println("Tiêu đề không được trùng");
                } else {
                    return title;
                }
            } else {
                System.err.println("Tiêu đề phải có từ 6 -> 50 ký tự");
            }
        } while (true);
    }



    public String inputAuthor(Scanner scanner) {
        System.out.println("Nhập vào tên tác giả: ");
        do {
            String author = scanner.nextLine();
            if (author.trim().isEmpty()) {
                System.err.println("Tên tác giả không được để trống");
            } else {
                return author;
            }
        } while (true);
    }

    public int inputYear(Scanner scanner) {
        System.out.println("Nhập vào năm xuất bản: ");
        do {
            int year = Integer.parseInt(scanner.nextLine());
            if (year >= 1970 && year <= LocalDate.now().getYear()) {
                return year;
            } else {
                System.err.println("Năm xuất bản phải từ 1970 cho đến " + LocalDate.now().getYear());
            }
        } while (true);
    }

    public String inputDescription(Scanner scanner) {
        System.out.println("Nhập vào mô tả quyển sách: ");
        do {
            String description = scanner.nextLine();
            if (description.trim().isEmpty()) {
                System.err.println("Mô tả không được để trống");
            } else {
                return description;
            }
        } while (true);
    }

    public Category inputCategory(Scanner scanner) {
        if (MenuCategory.indexCategory > 0) {
            System.out.println("Nhập vào id danh mục muốn thêm");
            do {
                int idCategory = Integer.parseInt(scanner.nextLine());
                Category categoryFind = null;
                for (int i = 0; i < MenuCategory.indexCategory; i++) {
                    if (MenuCategory.categories[i].getId() == idCategory) {
                        categoryFind = MenuCategory.categories[i];
                        break;
                    }
                }
                if (categoryFind != null) {
                    return categoryFind;
                } else {
                    System.err.println("Không tìm thấy danh mục sách đó");
                }
            } while (true);
        } else {
            System.err.println("Phải thêm danh mục trước");
        }
        return null;
    }
}
