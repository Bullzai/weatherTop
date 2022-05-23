package controllers;

import models.Member;
import models.Reading;
import models.Station;

import org.joda.time.DateTime;

import play.Logger;
import play.mvc.Controller;

import java.util.Date;

public class StationCtrl extends Controller {
  public static void index(Long id) {
    Member member = Accounts.getLoggedInMember(); // redirects them to login page if not logged in (prevent method)
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

  public static void deleteReport(Long stationid, Long readingid) {
    Station station = Station.findById(stationid);
    Reading reading = Reading.findById(readingid);
    Logger.info ("Removing reading #" + reading.id);
    station.readings.remove(reading);
    station.save();
    reading.delete();
    redirect("/station/" + stationid);
  }
}