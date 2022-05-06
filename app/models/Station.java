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

        return 0;
    }
}