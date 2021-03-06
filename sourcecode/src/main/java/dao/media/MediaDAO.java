package dao.media;

import entity.db.AIMSDB;
import entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class MediaDAO {

    private static MediaDAO instance;

    protected MediaDAO() {
    }

    public static MediaDAO getInstance() {
        if (instance == null) {
            instance = new MediaDAO();
        }
        return instance;
    }

    public List<Media> getAllMedia() throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery("select * from Media");
        // clean code: kieu du lieu ko ro rang
//        ArrayList medium = new ArrayList<>();
        ArrayList<Media> medium = new ArrayList<>();
        while (res.next()) {
            Media media = new Media(
                res.getInt("id"),
                res.getString("title"),
                res.getInt("quantity"),
                res.getString("category"),
                res.getString("imageUrl"),
                res.getInt("price"),
                res.getString("type"));
            medium.add(media);
        }
        return medium;
    }

    // coupling: data -> chi phu thuoc mot so tham so
    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM Media ;";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);

        if (res.next()) {
            return new Media(
                res.getInt("id"),
                res.getString("title"),
                res.getInt("quantity"),
                res.getString("category"),
                res.getString("imageUrl"),
                res.getInt("price"),
                res.getString("type"));
        }
        return null;
    }

    //stamp coupling: truyen tham so Object
    /**
     * SOLID: LSP vi neu cac doi tuong BookDAO, CDDAO, DVDDAO dung chuc nang updateMediaFieldById se bi loi
     * SOLID: OCP neu value khong phai la mot Stirng, thi phuong thuc toString() cua value se duoc goi. Neu khong muon dung phuong thuc toString mac dinh thi can phai sua code o phan nay de co the chay dung
     * */
    // clean code: loai bo phuong thuc ko su dung
   /* public void updateMediaFieldById(String tbname, int id, String field, Object value) throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        if (value instanceof String){
            value = "\"" + value + "\"";
        }
        stm.executeUpdate(" update Media set" + " "
                + field + "=" + value + " "
                + "where id=" + id + ";");
    }*/
}
