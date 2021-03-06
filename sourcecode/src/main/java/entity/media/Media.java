package entity.media;

import dao.media.MediaDAOFactory;
import dao.media.MediaDaoEnum;
import entity.db.AIMSDB;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * The general media class, for another media it can be done by inheriting this class
 */
public class Media {

  // clean code: bo phuong thuc ko su dung
//    private static Logger LOGGER = Utils.getLogger(Media.class.getName());

  protected Statement stm;
  protected int id;
  protected String title;
  protected String category;
  protected int value; // the real price of product (eg: 450)
  protected int price; // the price which will be displayed on browser (eg: 500)
  protected int quantity;
  protected String type;
  protected String imageURL;
  protected boolean rushSupported;

  public Media() throws SQLException {
    stm = AIMSDB.getConnection().createStatement();
  }

  // coupling: data -> chi phu thuoc mot so tham so
  public Media(int id, String title, String category, int price, int quantity, String type) throws SQLException {
    this.id = id;
    this.title = title;
    this.category = category;
    this.price = price;
    this.quantity = quantity;
    this.type = type;
  }

  // coupling: data -> chi phu thuoc mot so tham so
  public Media(int id, String title, int quantity, String category, String imageUrl, int price, String type) throws SQLException {
    this(id, title, category, price, quantity, type);
    this.imageURL = imageUrl;
  }

  public int getQuantity() throws SQLException {
    int updated_quantity = MediaDAOFactory.getMediaDao(MediaDaoEnum.media).getMediaById(id).quantity;
    this.quantity = updated_quantity;
    return updated_quantity;
  }

  // coupling: data -> chi phu thuoc mot so tham so
  public Media setQuantity(int quantity) {
    this.quantity = quantity;
    return this;
  }

  // coupling: data -> chi phu thuoc mot so tham so
  // clean code: bo phuong thuc ko su dung
//    private Media setId(int id){
//        this.id = id;
//        return this;
//    }

  // getter and setter
  public int getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  // clean code: bo phuong thuc ko su dung
//    public String getCategory() {
//        return this.category;
//    }

  // coupling: data -> chi phu thuoc mot so tham so
  // clean code: bo phuong thuc ko su dung
//    public Media setCategory(String category) {
//        this.category = category;
//        return this;
//    }

  // coupling: data -> chi phu thuoc mot so tham so
  public Media setTitle(String title) {
    this.title = title;
    return this;
  }

  public int getPrice() {
    return this.price;
  }

  // coupling: data -> chi phu thuoc mot so tham so
  public Media setPrice(int price) {
    this.price = price;
    return this;
  }

  // coupling: data -> chi phu thuoc mot so tham so
  // clean code: bo phuong thuc ko su dung
//    public Media setMediaURL(String url){
//        this.imageURL = url;
//        return this;
//    }

  public String getImageURL() {
    return this.imageURL;
  }

  public String getType() {
    return this.type;
  }

  // coupling: data -> chi phu thuoc mot so tham so
  public Media setType(String type) {
    this.type = type;
    return this;
  }

  // clean code: tranh truy cap qua sau vao ung dung
  public boolean includeTitle(String text) {
    return this.getTitle().toLowerCase().startsWith(text.toLowerCase());
  }
}
