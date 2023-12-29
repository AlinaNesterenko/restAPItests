package models;

import lombok.Data;

@Data
public class UserDataModel {
  String name, job;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  //{"name": "morpheus", "job": "leader"

}
