package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

import static controllers.Dashboard.getLatestWeather;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");

    List<Station> stations = Station.findAll();
    List<Station> latestStationWeather = new ArrayList<>();

    latestStationWeather = getLatestWeather(stations);

    render ("dashboard.html", stations, latestStationWeather);
  }

  public static List<Station> getLatestWeather(List<Station> station) {
    List<Station> tempList = new ArrayList<>();

    for (Station s : station) {
      Reading r = s.readings.get(s.readings.size()-1);
      s.readings.clear();
      s.readings.add(r);
      tempList.add(s);
      }
    return tempList;
  }
}
