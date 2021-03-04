package entity.media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CD extends Media {

    String artist;
    String recordLabel;
    String musicType;
    Date releasedDate;

    public CD() throws SQLException{

    }

    // coupling: data -> chỉ phụ thuộc vào một số tham số
    public CD(int id, String title, String category, int price, int quantity, String type, String artist,
            String recordLabel, String musicType, Date releasedDate) throws SQLException{
        super(id, title, category, price, quantity, type);
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.musicType = musicType;
        this.releasedDate = releasedDate;
    }

    public String getArtist() {
        return this.artist;
    }

    // coupling: data -> chỉ phụ thuộc vào một số tham số
    public CD setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getRecordLabel() {
        return this.recordLabel;
    }

    // coupling: data -> chỉ phụ thuộc vào một số tham số
    public CD setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
        return this;
    }

    public String getMusicType() {
        return this.musicType;
    }

    // coupling: data -> chỉ phụ thuộc vào một số tham số
    public CD setMusicType(String musicType) {
        this.musicType = musicType;
        return this;
    }

    public Date getReleasedDate() {
        return this.releasedDate;
    }

    public CD setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
        return this;
    }

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
