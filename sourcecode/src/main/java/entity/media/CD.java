package entity.media;

import java.sql.SQLException;
import java.util.Date;

public class CD extends Media {

  String artist;
  String recordLabel;
  String musicType;
  Date releasedDate;

  // clean code: bo phuong thuc ko su dung
//    public CD() throws SQLException{
//
//    }

  // coupling: data -> chi phu thuoc mot so tham so
  public CD(int id, String title, String category, int price, int quantity, String type, String artist,
            String recordLabel, String musicType, Date releasedDate) throws SQLException {
    super(id, title, category, price, quantity, type);
    this.artist = artist;
    this.recordLabel = recordLabel;
    this.musicType = musicType;
    this.releasedDate = releasedDate;
  }

  public String getArtist() {
    return this.artist;
  }

  // coupling: data -> chi phu thuoc mot so tham so
  public CD setArtist(String artist) {
    this.artist = artist;
    return this;
  }

  // clean code: bo phuong thuc ko su dung
//    public String getRecordLabel() {
//        return this.recordLabel;
//    }

  // coupling: data -> chi phu thuoc mot so tham so
  // clean code: bo phuong thuc ko su dung
//    public CD setRecordLabel(String recordLabel) {
//        this.recordLabel = recordLabel;
//        return this;
//    }

  // clean code: bo phuong thuc ko su dung
//    public String getMusicType() {
//        return this.musicType;
//    }

  // coupling: data -> chi phu thuoc mot so tham so
  // clean code: bo phuong thuc ko su dung
//    public CD setMusicType(String musicType) {
//        this.musicType = musicType;
//        return this;
//    }

  // clean code: bo phuong thuc ko su dung
//    public Date getReleasedDate() {
//        return this.releasedDate;
//    }

  //stamp coupling: truyen doi tuong Date
  // clean code: bo phuong thuc ko su dung
/*
    public CD setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
        return this;
    }
*/

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
    return "{" + basicInformation + " artist='" + artist + "'" + ", recordLabel='" + recordLabel + "'"
      + "'" + ", musicType='" + musicType + "'" + ", releasedDate='"
      + releasedDate + "'" + "}";
  }
}
