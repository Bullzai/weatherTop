package controllers;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();

    if (member == null) {
      Accounts.logout(); // log them out if web app was restarted and newly created account cookie was still set
    }

    List<Station> stations = member.stations;

    if (stations.size() > 0) { // check if there is a station
      Collections.sort(stations, new Comparator<Station>() {
        @Override
        public int compare(Station object1, Station object2) {
          return object1.name.compareTo(object2.name); // compare the 2 station objects using their name
        }
      });
    }

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
