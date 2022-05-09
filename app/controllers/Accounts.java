package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {
  public static void signup() {
    render("signup.html");
  }

  public static void login() {
    if (session.contains("logged_in_Memberid")) {
      redirect("/dashboard");
    } else {
      render("login.html");
    }
  }

  public static void register(String email, String password, String firstname, String lastname) {
    Logger.info("Registering new user " + email);
    Member member = new Member(email, password, firstname, lastname);
    member.save();
    session.put("logged_in_Memberid", member.id);
    redirect("/dashboard");
  }

  public static void authenticate(String email, String password) {
    Logger.info("Attempting to authenticate with " + email + ":" + password);

    Member member = Member.findByEmail(email);
    if ((member != null) && (member.checkPassword(password) == true)) {
      Logger.info("Authentication successful");
      session.put("logged_in_Memberid", member.id);
      redirect("/dashboard");
    } else {
      Logger.info("Authentication failed");
      redirect("/login");
    }
  }

  public static void logout() {
    session.clear();
    redirect("/");
  }

  public static Member getLoggedInMember() {
    Member member = null;
    if (session.contains("logged_in_Memberid")) {
      String memberId = session.get("logged_in_Memberid");
      member = Member.findById(Long.parseLong(memberId));
    } else {
      login();
    }
    return member;
  }
}