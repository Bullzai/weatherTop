package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");

    List<Station> stations = Station.findAll();
    List<Station> latestWeather = latestWeather(stations);


    Logger.info("aa", latestWeather);
    render ("dashboard.html", stations);
  }

  public static List<Reading> latestWeather(List<Station> station) {
    for (Station s : station){
      int lastIndex = s.readings.size() - 1;
      return (List<Reading>) s.readings.get(lastIndex);
    }
    return null;
  }

}
