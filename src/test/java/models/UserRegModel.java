package models;
import lombok.Data;

@Data
public class UserRegModel {
  String email, password, id, token;
}