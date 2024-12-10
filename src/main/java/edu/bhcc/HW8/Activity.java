package edu.bhcc.HW8;

import jakarta.persistence.*;

/**
 * Record class for use by JPA database.
 * Has attributes for id (primary key), route (name of place or route for the activity), miles (distance covered in
 * activity), and date (date the activity was done).
 */
@Entity
@Table(name = "activity")
public class Activity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String route;
  private Double miles;
  private String date;

  /**
   * Default constructor
   */
  protected Activity() {
  }

  /**
   * Parameterized constructor for creating new records.
   */
  public Activity(String route, Double miles, String date) {
    this.route = route;
    this.miles = miles;
    this.date = date;
  }

  // Getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRoute() {
    return route;
  }

  public void setRoute(String route) {
    this.route = route;
  }

  public Double getMiles() {
    return miles;
  }

  public void setMiles(Double miles) {
    this.miles = miles;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  /**
   * toString method to print a neatly formatted summary of an activity.
   *
   * @return the formatted string of attributes
   */
  @Override
  public String toString() {
    return String.format(
        "Activity [\n\tid:%d,\n\troute:'%s',\n\tmiles:%f,\n\tdate:'%s'\n]",
        id, route, miles, date
    );
  }
}
