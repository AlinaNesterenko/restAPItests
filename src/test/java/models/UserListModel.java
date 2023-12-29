package models;

import lombok.Data;

    import java.util.List;

@Data
public class UserListModel {
  Integer page, per_page, total, total_pages;
  Support support;
  List<DataInfo> data;

  @Data
  public static class DataInfo {
    Integer id;
    String email, first_name, last_name, avatar;
  }

  @Data
  public static class Support {
    String url, text;
  }
}