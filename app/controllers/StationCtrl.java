package controllers;

import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Station {
    public class StationCtrl extends Controller {
        public static void index() {
            Logger.info("Rendering station");

            List<models.Station> stations = models.Station.findAll();

            render("station.html", stations);
        }
    }
}
