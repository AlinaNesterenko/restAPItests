package models;

public class UserResponseModel {

  String page;
  String per_page;
  String total;

  public String getPage() {
    return page;
  }

  public void setPage(String page) {
    this.page = page;
  }

  public String getPer_page() {
    return per_page;
  }

  public void setPer_page(String per_page) {
    this.per_page = per_page;
  }

  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }

  public String getTotal_pages() {
    return total_pages;
  }

  public void setTotal_pages(String total_pages) {
    this.total_pages = total_pages;
  }

  String total_pages;


}