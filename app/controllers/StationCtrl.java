package controllers;

import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class StationCtrl extends Controller {
  public static void index(Long id) {

    Station station = Station.findById(id);

    Logger.info("Rendering Station");
    Logger.info("Station id = " + id);
    render("station.html", station);
  }
}