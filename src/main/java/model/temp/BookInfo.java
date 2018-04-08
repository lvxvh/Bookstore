package model.temp;

/**
 * Created by lxh on 2017/7/10.
 */
public class BookInfo {
    private int bookId;
    private String image;
    private String title;
    private int price;
    private String category;

    public BookInfo(int bookId, String image, String title, int price, String category) {
        this.bookId = bookId;
        this.image = image;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
