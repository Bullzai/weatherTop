/**
 * StationUtil utility class holds the most important methods
 * that are used in 'play' environment .html view files
 *
 * @author Vidmantas Valskis
 * @version 1.0
 */
package utils;

import models.Reading;
import models.Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static java.lang.Math.pow;
import static java.lang.Math.round;

public class StationUtil {

  public static float temperature(Station station) {
    if (!station.readings.isEmpty()) {
      return station.readings.get(station.readings.size() - 1).temperature;
    }
    return 0;
  }

  public static float pressure(Station station) {
    if (!station.readings.isEmpty()) {
      return station.readings.get(station.readings.size() - 1).pressure;
    }
    return 0;
  }

  public static float fahrenheit(Station station) {
    if (!station.readings.isEmpty()) {
      float cels = station.readings.get(station.readings.size() - 1).temperature;
      return (cels * 9 / 5 + 32);
    }
    return 0;
  }

  /**
   * Reads in the station and returns appropriate
   * display text and fomantic ui icon name
   *
   * @param station - the current station
   * @return A string array, where:
   * string[0] returns the actual message to be displayed
   * string[1] returns fomantic ui icon name to be used
   */
  public static String[] weather(Station station) {
    int code = 0;
    String[] weather = new String[2];

    if (!station.readings.isEmpty()) {
      code = station.readings.get(station.readings.size() - 1).code;
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
        weather[1] = "";
    }
    return weather;
  }

  public static int beaufort(Station station) {
    int beaufort = 0;
    float windSpeed = 0;

    if (!station.readings.isEmpty()) {
      windSpeed = station.readings.get(station.readings.size() - 1).windSpeed;
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

  public static String beaufortLabel(int beaufort) {
    // Create a HashMap object called beaufortHashMap
    HashMap<Integer, String> beaufortHashMap = new HashMap<Integer, String>();

    // Add keys and values (beaufort, label)
    beaufortHashMap.put(0, "Calm");
    beaufortHashMap.put(1, "Light Air");
    beaufortHashMap.put(2, "Light Breeze");
    beaufortHashMap.put(3, "Gentle Breeze");
    beaufortHashMap.put(4, "Moderate Breeze");
    beaufortHashMap.put(5, "Fresh Breeze");
    beaufortHashMap.put(6, "Strong Breeze");
    beaufortHashMap.put(7, "Near Gale");
    beaufortHashMap.put(8, "Gale");
    beaufortHashMap.put(9, "Severe Gale");
    beaufortHashMap.put(10, "Strong Storm");
    beaufortHashMap.put(11, "Violent Storm");

    return beaufortHashMap.get(beaufort);
  }

  public static String direction(Station station) {
    String direction = null;
    int windDirection = 0;

    if (!station.readings.isEmpty()) {
      windDirection = station.readings.get(station.readings.size() - 1).windDirection;
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

  public static double feelsLike(Station station) {
    float temperature = 0;
    float windSpeed = 0;

    if (!station.readings.isEmpty()) {
      temperature = station.readings.get(station.readings.size() - 1).temperature;
      windSpeed = station.readings.get(station.readings.size() - 1).windSpeed;
    } else {
      return 0;
    }

    double power = pow(windSpeed, 0.16);
    return round((13.12 + 0.6215 * temperature - 11.37 * power + 0.3965 * temperature * power) * 100.0) / 100.0;
  }

  public static double roundedLatitude(Station station) {
    return (int) (station.latitude * 1000.0) / 1000.0;
  }

  public static double roundedLongitude(Station station) {
    return (int) (station.longitude * 1000.0) / 1000.0;
  }

  public static String minMax(String maxValueString, Station station) {
    try {
      ArrayList<Float> arr = new ArrayList<Float>();

      for (Reading reading : station.readings) {
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

  public static String trend(String trendField, Station station) {
    ArrayList<Float> arr = new ArrayList<Float>();

    for (Reading reading : station.readings) {
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

