package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

import java.util.Date;

@Entity
public class Reading extends Model {
  public Date date;
  public int code;
  public float temperature;
  public float windSpeed;
  public int windDirection;
  public int pressure;

  public Reading(Date date, int code, float temperature, float windSpeed, int windDirection, int pressure) {
    this.date = date;
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.pressure = pressure;
  }
}