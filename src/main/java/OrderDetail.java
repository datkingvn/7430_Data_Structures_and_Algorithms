import ArrayList.AbstractArrayList;
import ArrayList.ArrayListEnhanced;

public class OrderDetail {
    private int id;
    private String customerName;
    private String shippingAddress;
    private AbstractArrayList<Book> books;
    private String status;

    public OrderDetail(int id, String customerName, String shippingAddress) {
        this.id = id;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.books = new ArrayListEnhanced<>();
        this.status = "Waiting"; // Default status is Waiting
    }

    public void addBook(Book book, int quantity, double price) {
        books.add(new Book(book.getId(), book.getTitle(), book.getAuthor(), price, quantity));
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public AbstractArrayList<Book> getBooks() {
        return books;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < books.size(); i++) {
            totalPrice += books.get(i).getPrice() * books.get(i).getQuantity();
        }
        return totalPrice;
    }

    public void sortBooksByTitle() {

        for (int i = 1; i < books.size(); i++) {
            Book currentBook = books.get(i);
            int j = i - 1;

            // This loop shifts elements that are greater than the currentBook to the right
            while (j >= 0 && books.get(j).getTitle().compareToIgnoreCase(currentBook.getTitle()) > 0) {
                books.set(j + 1, books.get(j));
                j--;
            }
            // Insert currentBook at the correct position
            books.set(j + 1, currentBook);
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("╭─────────────────────────────────────\n");
        stringBuilder.append(String.format("│ ID: %-31d \n", id));
        stringBuilder.append(String.format("│ Customer Name: %-19s \n", customerName));
        stringBuilder.append(String.format("│ Shipping Address: %-17s \n", shippingAddress));
        stringBuilder.append(String.format("│ => Books: %-28s \n", books));
        stringBuilder.append(String.format("│ Total Price: %.2f \n", getTotalPrice()));
        stringBuilder.append(String.format("│ Status: %-29s \n", status));
        stringBuilder.append("╰─────────────────────────────────────");

        return stringBuilder.toString();
    }
}
