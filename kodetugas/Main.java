import java.util.Scanner;
import java.util.ArrayList;

class Main {
    static ArrayList<Book> bookList = new ArrayList<>();
    static ArrayList<Student> userStudent = new ArrayList<>();

    public static void main(String[] args) {
        initializeData();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Library System =====");
            System.out.println("1. Student Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");
            System.out.print("Select option (1-3): ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    enterNim(scanner);
                    break;
                case "2":
                    Admin admin = new Admin();
                    admin.adminMenu(scanner);
                    break;
                case "3":
                    System.out.println("Thank you. Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void enterNim(Scanner scanner) {
        System.out.print("Enter your Student ID (input 99 to go back): ");
        String nim = scanner.nextLine();
        if (nim.equals("99")) {
            return;
        } else if (checkNim(nim)) {
            Student student = new Student(nim);
            student.studentMenu(scanner);
        } else {
            System.out.println("Invalid Student ID. Please try again.");
            enterNim(scanner);
        }
    }

    public static boolean checkNim(String nim) {
        for (Student student : userStudent) {
            if (student.getNim().equals(nim)) {
                return true;
            }
        }
        return false;
    }

    public static void initializeData() {
        bookList.add(new Book("388c-e681-9152", "title1", "author1", "History", 8));
        bookList.add(new Book("ed90-be30-5cdb", "title2", "author2", "History", 11));
        bookList.add(new Book("d95e-0c4a-9523", "title3", "author3", "History", 3));

        userStudent.add(new Student("Taufiq Ramadhan", "202210370311288", "Engineering", "Informatics"));
        userStudent.add(new Student("Who", "200510370318521", "Engineering", "Informatics"));
        userStudent.add(new Student("Sutrisno Adit Pratama", "202210370311203", "Engineering", "Informatics"));
    }
}

class Book {
    private String id;
    private String title;
    private String author;
    private String category;
    private int stock;

    public Book(String id, String title, String author, String category, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public int getStock() { return stock; }
}

class Student {
    private String name;
    private String nim;
    private String faculty;
    private String program;

    public Student(String name, String nim, String faculty, String program) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.program = program;
    }

    public Student(String nim) {
        for (Student student : Main.userStudent) {
            if (student.getNim().equals(nim)) {
                this.name = student.getName();
                this.nim = student.getNim();
                this.faculty = student.getFaculty();
                this.program = student.getProgram();
                break;
            }
        }
    }

    public String getNim() { return nim; }

    public void studentMenu(Scanner scanner) {
        while (true) {
            System.out.println("=== Student Menu ===");
            System.out.println("1. Borrowed Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Borrow a Book or Logout");
            System.out.print("Select option (1-3): ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.println("Borrowed Books:");
                    break;
                case "2":
                    displayBooks();
                    break;
                case "3":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void displayBooks() {
        System.out.println("=======================================================================================================");
        System.out.printf("| %-4s | %-10s | %-30s | %-20s | %-15s | %-6s |\n", "No.", "Book ID", "Book Title", "Author", "Category", "Stock");
        System.out.println("=======================================================================================================");
        int index = 1;
        for (Book book : Main.bookList) {
            System.out.printf("| %-4d | %-10s | %-30s | %-20s | %-15s | %-6d |\n", index, book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
            index++;
        }
        System.out.println("=======================================================================================================");
    }
    

    public String getName() { return name; }
    public String getFaculty() { return faculty; }
    public String getProgram() { return program; }
}

class Admin {
    public void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("=== Admin Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. Display Registered Students");
            System.out.println("3. Logout");
            System.out.print("Select option (1-3): ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    addStudent(scanner);
                    break;
                case "2":
                    displayStudent();
                    break;
                case "3":
                    System.out.println("Logging out from admin account.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void addStudent(Scanner scanner) {
        System.out.println("Enter student details:");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        String nim;
        do {
            System.out.print("Enter student ID: ");
            nim = scanner.nextLine();
            if (nim.length() != 15) {
                System.out.println("Student ID must be 15 digits.");
            }
        } while (nim.length() != 15);
        System.out.print("Enter student faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Enter student program: ");
        String program = scanner.nextLine();
        Main.userStudent.add(new Student(name, nim, faculty, program));
        System.out.println("Student successfully registered.");
    }

    public void displayStudent() {
        System.out.println("List of Registered Students:");
        for (Student student : Main.userStudent) {
            System.out.println("Name: " + student.getName());
            System.out.println("Faculty: " + student.getFaculty());
            System.out.println("ID: " + student.getNim());
            System.out.println("Program: " + student.getProgram());
            System.out.println();
        }
    }
}