/**
 * Station class holds the most important methods
 * that are used in 'play' environment .html
 *
 * @author Vidmantas Valskis
 * @version 1.0
 */
package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

import static java.lang.Math.pow;
import static java.lang.Math.round;

@Entity
public class Station extends Model {
  public String name;
  public double latitude;
  public double longitude;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();

  public Station(String name) {
    this.name = name;
  }

  public Station(String name, double latitude, double longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public int lastIndex() {
    if (!readings.isEmpty()) {
      return readings.size() - 1;
    }
    return 0;
  }

  public float fahrenheit() {
    if (!readings.isEmpty()) {
      float cels = readings.get(readings.size() - 1).temperature;
      return (cels * 9 / 5 + 32);
    }
    return 0;
  }

  /**
   * Reads in latest readings code value and returns
   * appropriate display text and fomantic ui icon name
   *
   * @return A string array, where:
   * string[0] returns the actual message to be displayed
   * string[1] returns fomantic ui icon name to be used
   */
  public String[] weather() {
    int code = 0;
    String[] weather = new String[2];

    if (!readings.isEmpty()) {
      code = readings.get(readings.size() - 1).code;
    }

    switch (code) {
      case 100:
        weather[0] = "Clear";
        weather[1] = "sun";
        break;
      case 200:
        weather[0] = "Partial Clouds";
        weather[1] = "cloud sun";
        break;
      case 300:
        weather[0] = "Cloudy";
        weather[1] = "cloud";
        break;
      case 400:
        weather[0] = "Light Showers";
        weather[1] = "cloud sun rain";
        break;
      case 500:
        weather[0] = "Heavy Showers";
        weather[1] = "cloud showers heavy";
        break;
      case 600:
        weather[0] = "Rain";
        weather[1] = "cloud rain";
        break;
      case 700:
        weather[0] = "Snow";
        weather[1] = "snowflake";
        break;
      case 800:
        weather[0] = "Thunder";
        weather[1] = "bolt";
        break;
      default:
        weather[0] = "";
        weather[1] = "meteor";
    }
    return weather;
  }

  public int beaufort() {
    int beaufort = 0;
    float windSpeed = 0;

    if (!readings.isEmpty()) {
      windSpeed = readings.get(readings.size() - 1).windSpeed;
    }
    if (1 < windSpeed && windSpeed < 5) {
      beaufort = 1;
    } else if (6 < windSpeed && windSpeed < 11) {
      beaufort = 2;
    } else if (12 < windSpeed && windSpeed < 19) {
      beaufort = 3;
    } else if (20 < windSpeed && windSpeed < 28) {
      beaufort = 4;
    } else if (29 < windSpeed && windSpeed < 38) {
      beaufort = 5;
    } else if (39 < windSpeed && windSpeed < 49) {
      beaufort = 6;
    } else if (50 < windSpeed && windSpeed < 61) {
      beaufort = 7;
    } else if (62 < windSpeed && windSpeed < 74) {
      beaufort = 8;
    } else if (75 < windSpeed && windSpeed < 88) {
      beaufort = 9;
    } else if (89 < windSpeed && windSpeed < 102) {
      beaufort = 10;
    } else if (103 < windSpeed && windSpeed < 117) {
      beaufort = 11;
    }
    return beaufort;
  }

  public String direction() {
    String direction = null;
    int windDirection = 0;

    if (!readings.isEmpty()) {
      windDirection = readings.get(readings.size() - 1).windDirection;
    }
    if (348.75 < windDirection && windDirection < 11.25) {
      direction = "North";
    } else if (11.25 < windDirection && windDirection < 33.75) {
      direction = "North North East";
    } else if (33.75 < windDirection && windDirection < 56.25) {
      direction = "North East";
    } else if (56.25 < windDirection && windDirection < 78.75) {
      direction = "East North East";
    } else if (78.75 < windDirection && windDirection < 101.25) {
      direction = "East";
    } else if (101.25 < windDirection && windDirection < 123.75) {
      direction = "East South East";
    } else if (123.75 < windDirection && windDirection < 146.25) {
      direction = "South East";
    } else if (146.25 < windDirection && windDirection < 168.75) {
      direction = "South South East";
    } else if (168.75 < windDirection && windDirection < 191.25) {
      direction = "South";
    } else if (191.25 < windDirection && windDirection < 213.75) {
      direction = "South South West";
    } else if (213.75 < windDirection && windDirection < 236.25) {
      direction = "South West";
    } else if (236.25 < windDirection && windDirection < 258.75) {
      direction = "West South West";
    } else if (258.75 < windDirection && windDirection < 281.25) {
      direction = "West";
    } else if (281.25 < windDirection && windDirection < 303.75) {
      direction = "West North West";
    } else if (303.75 < windDirection && windDirection < 326.25) {
      direction = "North West";
    } else if (326.25 < windDirection && windDirection < 348.75) {
      direction = "North North West";
    }
    return direction;
  }

  public double feelsLike() {
    float temperature = 0;
    float windSpeed = 0;

    if (!readings.isEmpty()) {
      temperature = readings.get(readings.size() - 1).temperature;
      windSpeed = readings.get(readings.size() - 1).windSpeed;
    }

    double power = pow(windSpeed, 0.16);
    return round((13.12 + 0.6215 * temperature - 11.37 * power + 0.3965 * temperature * power) * 100.0) / 100.0;
  }

  public double roundedLatitude() {
    return (int) (this.latitude * 1000.0) / 1000.0;
  }

  public double roundedLongitude() {
    return (int) (this.longitude * 1000.0) / 1000.0;
  }

  public String minMax(String maxValueString) {
    try {
      ArrayList<Float> arr = new ArrayList<Float>();

      for (Reading reading : readings) {
        switch (maxValueString) {
          case "temperature":
            arr.add(reading.temperature);
            break;
          case "windSpeed":
            arr.add(reading.windSpeed);
            break;
          case "pressure":
            arr.add((float) reading.pressure);
            break;
        }
      }

      Collections.sort(arr);
      /*
      First element in the ArrayList is the minimum value
      Last element in the ArrayList is the maximum value
       */
      return "Max: " + arr.get(arr.size() - 1) + " Min: " + arr.get(0);
    } catch (Exception e) {
      return "";
    }
  }

  public String trend(String trendField) {
    ArrayList<Float> arr = new ArrayList<Float>();

    for (Reading reading : readings) {
      switch (trendField) {
        case "temperature":
          arr.add(reading.temperature);
          break;
        case "windSpeed":
          arr.add(reading.windSpeed);
          break;
        case "pressure":
          arr.add((float) reading.pressure);
          break;
      }
    }

    int arrSize = arr.size();

    try {
      if (arr.get(arrSize - 3) > arr.get(arrSize - 2) && arr.get(arrSize - 2) > arr.get(arrSize - 1)) {
        return "down";
      } else if (arr.get(arrSize - 3) < arr.get(arrSize - 2) && arr.get(arrSize - 2) < arr.get(arrSize - 1)) {
        return "up";
      }
    } catch (Exception e) {
    }
    return "";
  }
}

