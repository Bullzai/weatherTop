package controllers;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.Collections;
import java.util.List;

public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    render("dashboard.html", stations);
  }

  public static void addStation(String name, double latitude, double longitude) {
    Logger.info("Adding a station");
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(name, latitude, longitude);
    member.stations.add(station);
    member.save();
    redirect("/dashboard");
  }

  public static void deleteStation(Long id) {
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    Logger.info("DELETING station " + station.name);
    member.stations.remove(station); // remove the station from this member
    member.save(); // save the changes
    station.delete(); // actually delete the station from DB
    redirect("/dashboard");
  }
}
