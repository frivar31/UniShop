package Data.Entities.Products;

import java.util.Date;

public class Book extends Product {

    private String ISBN;
    private String author;
    private String editor;
    private String genre;
    private Date pubDate;
    private int editorNum;
    private int volNum;

    public Book(String title,
                String desc,
                String category,
                Date date,
                int initialQuantity,
                double price,
                int points,
                String ISBN,
                String author,
                String editor,
                String genre,
                Date pubDate,
                int editorNum,
                int volNum
    ) {
        super(title, desc, category, date, initialQuantity, price, points);
        this.ISBN = ISBN;
        this.author = author;
        this.editor = editor;
        this.genre = genre;
        this.pubDate = pubDate;
        this.editorNum = editorNum;
        this.volNum = volNum;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public int getEditorNum() {
        return editorNum;
    }

    public void setEditorNum(int editorNum) {
        this.editorNum = editorNum;
    }

    public int getVolNum() {
        return volNum;
    }

    public void setVolNum(int volNum) {
        this.volNum = volNum;
    }


}
