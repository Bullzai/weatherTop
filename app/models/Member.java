package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model {
  public String email;
  public String password;
  public String firstname;
  public String lastname;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Station> stations = new ArrayList<Station>();

  public Member(String email, String password, String firstname, String lastname) {
    this.email = email;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public static Member findByEmail(String email) {
    return find("email", email).first();
  }

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }
}