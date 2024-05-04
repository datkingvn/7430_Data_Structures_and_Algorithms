import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookOrderSystem system = new BookOrderSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║ [1]. Add Book                                    ║");
            System.out.println("║ [2]. Place Order                                 ║");
            System.out.println("║ [3]. View All Orders                             ║");
            System.out.println("║ [4]. View Order Details                          ║");
            System.out.println("║ [5]. Process Next Order                          ║");
            System.out.println("║ [6]. Display All Books (Arranged from A - Z)     ║");
            System.out.println("║ [7]. Find Orders by Customer Name                ║");
            System.out.println("║ [0]. Exit                                        ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBook(system, scanner);
                    break;
                case 2:
                    placeOrder(system, scanner);
                    system.displayBooks();
                    break;

                case 3:
                    system.viewOrders();
                    break;
                case 4:
                    viewOrderDetails(system, scanner);
                    break;
                case 5:
                    system.processOrder();
                    break;
                case 6:
//                    long startTime = System.nanoTime();
                    system.displayBooks();
//                    long endTime = System.nanoTime();
//                    System.out.println("Add Book Time: " + (endTime - startTime) + " nanoseconds");
                    break;
                case 7:
                    System.out.print("Enter customer name to search: ");
                    scanner.nextLine();
                    String customerName = scanner.nextLine();
                    system.findOrdersByCustomerName(customerName);
                    break;
                case 0:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void addBook(BookOrderSystem system, Scanner scanner) {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        system.addBook(id, title, author, price, quantity);
    }

    private static void placeOrder(BookOrderSystem system, Scanner scanner) {
        System.out.print("Enter customer name: ");
        scanner.nextLine();
        String customerName = scanner.nextLine();
        System.out.print("Enter shipping address: ");
        String shippingAddress = scanner.nextLine();
        HashMap<Integer, Integer> bookOrders = new HashMap<>();
        String more;
        do {
            System.out.print("Enter book ID: ");
            int bookId = scanner.nextInt();
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            bookOrders.put(bookId, quantity);
            System.out.print("Add more books? (yes/no): ");
            more = scanner.next();
        } while (more.equalsIgnoreCase("yes"));
        system.placeOrder(customerName, shippingAddress, bookOrders);
    }

    private static void viewOrderDetails(BookOrderSystem system, Scanner scanner) {
        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();
        system.viewOrderDetail(orderId);
    }
}
