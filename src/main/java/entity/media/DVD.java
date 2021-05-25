package entity.media;

import java.sql.SQLException;
import java.util.Date;

public class DVD extends Media {

  String discType;
  String director;
  int runtime;
  String studio;
  String subtitles;
  Date releasedDate;
  String filmType;

  // clean code: bo phuong thuc ko su dung
    /*public DVD() throws SQLException{

    }*/

  // coupling: data -> chi phu thuoc mot so tham so
  public DVD(int id, String title, String category, int price, int quantity, String type, String discType,
             String director, int runtime, String studio, String subtitles, Date releasedDate, String filmType) throws SQLException {
    super(id, title, category, price, quantity, type);
    this.discType = discType;
    this.director = director;
    this.runtime = runtime;
    this.studio = studio;
    this.subtitles = subtitles;
    this.releasedDate = releasedDate;
    this.filmType = filmType;
  }

  // clean code: bo phuong thuc ko su dung
/*
    public String getDiscType() {
        return this.discType;
    }
*/

  // coupling: data -> chi phu thuoc mot so tham so
  // clean code: bo phuong thuc ko su dung
//    public DVD setDiscType(String discType) {
//        this.discType = discType;
//        return this;
//    }

  // clean code: bo phuong thuc ko su dung
//    public String getDirector() {
//        return this.director;
//    }

  // coupling: data -> chi phu thuoc mot so tham so
  // clean code: bo phuong thuc ko su dung
//    public DVD setDirector(String director) {
//        this.director = director;
//        return this;
//    }

  public int getRuntime() {
    return this.runtime;
  }

  // coupling: data -> chi phu thuoc mot so tham so
  public DVD setRuntime(int runtime) {
    this.runtime = runtime;
    return this;
  }

  // clean code: bo phuong thuc ko su dung
//    public String getStudio() {
//        return this.studio;
//    }

  // coupling: data -> chi phu thuoc mot so tham so
  // clean code: bo phuong thuc ko su dung
//    public DVD setStudio(String studio) {
//        this.studio = studio;
//        return this;
//    }

  // clean code: bo phuong thuc ko su dung
//    public String getSubtitles() {
//        return this.subtitles;
//    }

  // coupling: data -> chi phu thuoc mot so tham so
  // clean code: bo phuong thuc ko su dung
//    public DVD setSubtitles(String subtitles) {
//        this.subtitles = subtitles;
//        return this;
//    }

  // clean code: bo phuong thuc ko su dung
//    public Date getReleasedDate() {
//        return this.releasedDate;
//    }

  //stamp coupling: truyen doi tuong Date
  // clean code: bo phuong thuc ko su dung
//    public DVD setReleasedDate(Date releasedDate) {
//        this.releasedDate = releasedDate;
//        return this;
//    }

  // clean code: bo phuong thuc ko su dung
//    public String getFilmType() {
//        return this.filmType;
//    }


  // coupling: data -> chi phu thuoc mot so tham so
  // clean code: bo phuong thuc ko su dung
//    public DVD setFilmType(String filmType) {
//        this.filmType = filmType;
//        return this;
//    }

  @Override
  public String toString() {
    String basicInformation = "{" +
      " id='" + id + "'" +
      ", title='" + title + "'" +
      ", category='" + category + "'" +
      ", price='" + price + "'" +
      ", quantity='" + quantity + "'" +
      ", type='" + type + "'" +
      ", imageURL='" + imageURL + "'" +
      "}";
    return "{" + basicInformation + " discType='" + discType + "'" + ", director='" + director + "'" + ", runtime='"
      + runtime + "'" + ", studio='" + studio + "'" + ", subtitles='" + subtitles + "'" + ", releasedDate='"
      + releasedDate + "'" + ", filmType='" + filmType + "'" + "}";
  }
}
