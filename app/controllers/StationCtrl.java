package controllers;

import models.Reading;
import models.Station;
import org.joda.time.DateTime;
import play.Logger;
import play.mvc.Controller;

import java.util.Date;

public class StationCtrl extends Controller {
  public static void index(Long id) {
    Station station = Station.findById(id);
    Logger.info("Rendering Station " + id);
    render("station.html", station);
  }

  public static void submitReport(Long id, int code, float temperature, float windSpeed, int windDirection, int pressure) {
    Date date = DateTime.now().toDate();
    Reading reading = new Reading(date, code, temperature, windSpeed, windDirection, pressure);
    Station station = Station.findById(id);
    station.readings.add(reading);
    station.save();
    redirect("/station/" + id);
  }
}