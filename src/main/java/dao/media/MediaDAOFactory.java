package dao.media;

// design pattern: factory
public class MediaDAOFactory {
  public static MediaDAO getMediaDao(MediaDaoEnum type) {
    switch (type) {
      case media:
        return MediaDAO.getInstance();
      case cd:
        return CDDAO.getInstance();
      case dvd:
        return DVDDAO.getInstance();
      case book:
        return BookDAO.getInstance();
      default:
        return null;
    }
  }

  private MediaDAOFactory() {
  }
}
