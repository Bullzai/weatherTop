package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

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
}