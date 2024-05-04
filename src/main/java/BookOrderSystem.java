import java.util.*;

import ArrayList.*;
import Queue.AbstractQueue;
import Queue.Queue;

public class BookOrderSystem {
    private AbstractArrayList<Book> books;
    private Queue<OrderDetail> orders; // Use your custom Queue
    private int nextOrderId;

    public BookOrderSystem() {
        books = new ArrayListEnhanced<>(); // Correct
        orders = new Queue<OrderDetail>(); // Your custom Queue initialization
        nextOrderId = 1;
        initializeBooks();
    }

    private void initializeBooks() {
        books.add(new Book(1, "One Piece", "Eiichiro Oda", 10, 10));
        books.add(new Book(2, "Naruto", "Masashi Kishimoto", 15, 15));
        books.add(new Book(3, "Attack on Titan", "Hajime Isayama", 20, 1));
        books.add(new Book(4, "Dragon Ball", "Akira Toriyama", 8, 8));
        books.add(new Book(5, "Death Note", "Tsugumi Ohba", 12, 12));
        books.add(new Book(6, "My Hero Academia", "Kohei Horikoshi", 18, 18));
        books.add(new Book(7, "Fullmetal Alchemist", "Hiromu Arakawa", 25, 0));
        books.add(new Book(8, "One Punch Man", "ONE", 5, 5));
        books.add(new Book(9, "Bleach", "Tite Kubo", 10, 10));
        books.add(new Book(10, "Fairy Tail", "Hiro Mashima", 7, 7));
    }

    public void addBook(int id, String title, String author, double price, int quantity) {
        if (findBookById(id) != null) {
            System.out.println("Book ID already exists. Book not added.");
            return;
        }
        books.add(new Book(id, title, author, price, quantity));
        System.out.println("Book added: " + title);
    }

    public void placeOrder(String customerName, String shippingAddress, HashMap<Integer, Integer> bookOrders) {
        OrderDetail order = new OrderDetail(nextOrderId++, customerName, shippingAddress);
        boolean validOrder = true;
        List<Book> tempBooks = new ArrayList<>();

        // Check availability first
        for (Map.Entry<Integer, Integer> entry : bookOrders.entrySet()) {
            Book book = findBookById(entry.getKey());
            if (book != null && book.getQuantity() >= entry.getValue()) {
                tempBooks.add(new Book(book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), entry.getValue()));
            } else {
                System.out.println("❌ Insufficient stock for Book ID: " + entry.getKey());
                validOrder = false;
                break;
            }
        }

        // If all books are available, finalize the order
        if (validOrder) {
            tempBooks.forEach(book -> {
                Book originalBook = findBookById(book.getId());
                originalBook.setQuantity(originalBook.getQuantity() - book.getQuantity());
                order.addBook(book, book.getQuantity(), book.getPrice());
            });
            orders.offer(order);
            System.out.println("✅ Order placed: \n" + order);
        } else {
            System.out.println("❌ Order not placed due to issues with book stock.");
        }
    }

    private Book findBookById(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                return books.get(i);
            }
        }
        return null;
    }

    public void processOrder() {
        if (orders.isEmpty()) {
            System.out.println("No orders to process.");
            return;
        }
        OrderDetail order = orders.poll();
        order.setStatus("Shipping");
        System.out.println("Processing order: " + order);
    }

    public void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders to view.");
            return;
        }
        for (OrderDetail order : orders) {
            order.sortBooksByTitle();  // Sort books within each order
            System.out.println(order);
        }
    }

    public void viewOrderDetail(int orderId) {
        for (OrderDetail order : orders) {
            if (order.getId() == orderId) {
                order.sortBooksByTitle();  // Sort books within the order before displaying
                System.out.println(order);
                return;
            }
        }
        System.out.println("Order ID not found: " + orderId);
    }


    public void displayBooks() {
        // Insertion sort to sort books by title
        for (int i = 1; i < books.size(); i++) {
            Book key = books.get(i);
            int j = i - 1;

            // Compare books based on title
            while (j >= 0 && books.get(j).getTitle().compareTo(key.getTitle()) > 0) {
                books.set(j + 1, books.get(j));
                j = j - 1;
            }
            books.set(j + 1, key);
        }

        // Display the sorted list of books
        System.out.println("-------------------------------------------------");
        System.out.printf("%-5s | %-30s | %-20s | %-10s | %-10s%n",
                "ID", "Title", "Author", "Price", "Quantity");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.printf("%-5d | %-30s | %-20s | %-10.2f | %-10d%n",
                    book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getQuantity());
        }
        System.out.println("-------------------------------------------------");
    }

    public void findOrdersByCustomerName(String customerName) {
        boolean found = false;
        System.out.println("Searching for orders by customer: " + customerName);
        for (OrderDetail order : orders) {
            if (order.getCustomerName().equalsIgnoreCase(customerName)) {
                System.out.println(order);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No orders found for customer: " + customerName);
        }
    }
}
