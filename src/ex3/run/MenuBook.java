package ex3.run;

import ex3.entity.Book;

import java.util.Scanner;

public class MenuBook {
    public static Book[] books = new Book[0];
    public static int indexBooks = 0;

    public static void menuBook(Scanner scanner) {
        do {
            System.out.println("**************** MENU ****************");
            System.out.println("1. Thêm mới sách");
            System.out.println("2. Cập nhật thông tin sách");
            System.out.println("3. Xoá sách");
            System.out.println("4. Tìm kiếm sách");
            System.out.println("5. Hiển thị danh sách sách theo nhóm thể loại");
            System.out.println("6. Thoát");
            System.out.println("**************************************");
            System.out.println("Lựa chọn : ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addNewBooks(scanner);
                    break;
                case 2:
                    updateBooksById(scanner);
                    break;
                case 3:
                    deleteBookById(scanner);
                    break;
                case 4:
                    findBooks(scanner);
                    break;
                case 5:
                    showBookByCategoryName();
                    break;
                case 6:
                    MainMenu.menuMain(scanner);
                    break;
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 3");
            }
        } while (true);
    }

    public static void showListBook() {
        if (books != null) {
            for (Book c : books) {
                c.displayData();
            }
        }
    }

    public static void addNewBooks(Scanner scanner) {
        System.out.println("Nhập vào số lượng muốn thêm");
        int n = Integer.parseInt(scanner.nextLine());

        Book[] newbooks = new Book[books.length + n];

        // [1,2,3,4,...]
        if (indexBooks == 0) {
            for (int i = books.length; i < n; i++) {
                newbooks[i] = new Book();
                newbooks[i].inputData(scanner);
                books = newbooks;
                indexBooks++;
            }
        } else {
            for (int i = 0; i < books.length; i++) {
                newbooks[i] = books[i];
            }
            for (int i = books.length - 1; i < n; i++) {
                newbooks[i] = new Book();
                newbooks[i].inputData(scanner);
                books = newbooks;
                indexBooks++;
            }
        }
    }

    public static void updateBooksById(Scanner scanner) {
        System.out.println("Enter book id: ");
        String bookId = scanner.nextLine();
        int indexEdit = getIndexBookById(bookId);
        if (indexEdit != -1) {
            boolean isExit = true;
            books[indexEdit].displayData();
            do {
                System.out.println("1. Update Author Name");
                System.out.println("2. Update Title");
                System.out.println("3. Update Year");
                System.out.println("4. Update Description");
                System.out.println("5. Update Category");
                System.out.println("6. Exit");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        books[indexEdit].setAuthor(books[indexEdit].inputAuthor(scanner));
                        break;
                    case 2:
                        books[indexEdit].setTitle(books[indexEdit].updateProductTittle(scanner,indexEdit));
                        break;
                    case 3:
                        books[indexEdit].setYear(books[indexEdit].inputYear(scanner));
                        break;
                    case 4:
                        books[indexEdit].setDescription(books[indexEdit].inputDescription(scanner));
                        break;
                    case 5:
                        books[indexEdit].setCategory(books[indexEdit].inputCategory(scanner));
                        break;
                    case 6:
                        menuBook(scanner);
                        break;
                    default:
                        isExit = false;
                }
            } while (isExit);

        } else {
            System.out.println("There is no book with id to edit" + bookId);
        }

    }

    public static void deleteBookById(Scanner scanner) {
        System.out.println("Enter Book id to delete:");
        String bookId = scanner.nextLine();
        int indexDelete = getIndexBookById(bookId);
        if (indexDelete != -1) {
            for (int i = indexDelete; i < indexBooks-1; i++) {
                books[i] = books[i + 1];
            }
            indexBooks--;
        } else {
            System.out.println("There is no Book with id to delete" + bookId);
        }
    }

    public static int getIndexBookById(String indexChange) {
        for (int i = 0; i < indexBooks; i++) {
            if (books[i].getId().equalsIgnoreCase(indexChange)) {
                return i;
            }
        }
        return -1;
    }

    public static void findBooks(Scanner scanner){
        System.out.println("Enter something to find Book:");
        String bookSearch = scanner.nextLine();
        int count = 0;
        for (int i=0; i<books.length; i++) {
            if (books[i].getTitle().equalsIgnoreCase(bookSearch) || books[i].getAuthor().equalsIgnoreCase(bookSearch)) {
                books[i].displayData();
            }
        }
        System.out.println("There are " + count + " books has found");
    }

    public static void showBookByCategoryName(){
        Book[] newbooks = new Book[books.length];
        newbooks[0] = books[0];
        int newBooksIndex =0;
        for (Book book : books) {
            if (newbooks[newBooksIndex].getCategory() != book.getCategory()) {
                newBooksIndex++;
                newbooks[newBooksIndex] = book;
            }
        }
        for (int i = 0; i < newbooks.length; i++) {
            System.out.println((i+1)+". Category: "+newbooks[i].getCategory().getName());
            for (int j = 0; j < books.length; j++) {
                if (newbooks[newBooksIndex].getCategory() == books[i].getCategory()){
                    books[i].displayData();
                }
            }
        }

    }
}
