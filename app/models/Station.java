package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

import static java.lang.Math.pow;
import static java.lang.Math.round;

@Entity
public class Station extends Model
{
    public String name;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();

    public Station(String name) {
        this.name = name;
    }

    public int lastReading(){
        return readings.size() -1;
    }

    public float fahrenheit(float cels) {
        return (cels * 9/5 + 32);
    }

    public String weather(int code) {
        String weather = null;
        switch (code) {
            case 100: weather = "Clear";
                break;
            case 200: weather = "Partial Clouds";
                break;
            case 300: weather = "Cloudy";
                break;
            case 400: weather = "Light Showers";
                break;
            case 500: weather = "Heavy Showers";
                break;
            case 600: weather = "Rain";
                break;
            case 700: weather = "Snow";
                break;
            case 800: weather = "Thunder";
                break;
        }
        return weather;
    }

    public int beaufort (float windSpeed) {
        int beaufort = 0;
        if (1 < windSpeed && windSpeed < 5) {
            beaufort = 1;
        } else if (6 < windSpeed && windSpeed < 11){
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

    public String direction (float windDirection) {
        String direction = null;
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

    public double feelsLike (float temperature, float windSpeed) {
        double power = pow(windSpeed, 0.16);
        return round((13.12 + 0.6215*temperature - 11.37*power + 0.3965*temperature*power) * 100.0) / 100.0;
    }
}