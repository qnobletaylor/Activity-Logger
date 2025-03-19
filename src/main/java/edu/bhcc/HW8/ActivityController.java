package edu.bhcc.HW8;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for controlling requests to the server
 */
@Controller
public class ActivityController {
  private ActivityRepository repo;

  /**
   * Constructor for setting the repository
   */
  public ActivityController(ActivityRepository repo) {
    this.repo = repo;
  }

  /**
   * For getting the home page at endpoint '/'
   */
  @GetMapping("/")
  public String index(Model model) {
    List<Activity> activityList = getAllActivities();
    model.addAttribute("activity_list", activityList);

    double totalMiles = getTotalMiles(activityList);
    model.addAttribute("total_miles", totalMiles);
    return "index";
  }

  /**
   * Handles when a new activity gets added, a new instance gets created and added straight to the database
   */
  @GetMapping("/add_activity")
  public String addActivity(String route, Double miles, String date, Model model) {
    Activity activity = new Activity(route, miles, date);
    repo.save(activity); // Saves new record

    model.addAttribute("toast", "New activity added: " + route + "!");
    model.addAttribute("add", true);

    List<Activity> activityList = getAllActivities();
    model.addAttribute("activity_list", activityList); // add activities to template
    double totalMiles = getTotalMiles(activityList);
    model.addAttribute("total_miles", totalMiles); // gets total miles accumulated
    return "index";
  }

  /**
   * Handles deleting an activity, gets the id from the html page and deletes that record from the DB
   */
  @GetMapping("/delete_activity")
  public String deleteActivity(Integer id, Model model) {
    Activity activity = repo.findById(id);

    if (activity != null) {
      repo.delete(activity);

      model.addAttribute("toast", "Deleted activity: " + activity.getRoute() + " | " + activity.getDate());
      model.addAttribute("delete", true);
    } else {
      model.addAttribute("toast", "Could not find activity with ID: " + id + ".");
      model.addAttribute("error", true);
    }

    List<Activity> activityList = getAllActivities();
    model.addAttribute("activity_list", activityList);
    double totalMiles = getTotalMiles(activityList);
    model.addAttribute("total_miles", totalMiles);
    return "index";
  }

  /**
   * Updates an already existing activity.
   */
  @GetMapping("/update_activity")
  public String updateActivity(Integer id, String route, Double miles, String date, Model model) {
    Activity activity = repo.findById(id);
    String oldActivity = activity.toString(); // Stores the old activity attributes as string for toast purposes

    if (activity != null) { // Takes the reference to the activity and sets new values
      activity.setRoute(route);
      activity.setMiles(miles);
      activity.setDate(date);

      repo.save(activity);

      model.addAttribute("toast", "Updated Activity!");
      model.addAttribute("update", true);
    } else {
      model.addAttribute("toast", "Could not find activity with ID: " + id + ".");
      model.addAttribute("error", true);
    }

    List<Activity> activityList = getAllActivities();
    model.addAttribute("activity_list", activityList);
    double totalMiles = getTotalMiles(activityList);
    model.addAttribute("total_miles", totalMiles);
    return "index";
  }

  /**
   * Simply returns an arraylist of all records currently in the database.
   */
  private List<Activity> getAllActivities() {
    Iterable<Activity> activityIter = repo.findAll(); // Records put into an iterable
    List<Activity> activityList = new ArrayList<>();

    activityIter.forEach(activity -> activityList.add(activity)); // Transfer each record to arraylist

    return activityList;
  }

  private double getTotalMiles(List<Activity> activityList) {
    double totalMiles = 0;
    for (Activity activity : activityList) {
      totalMiles += activity.getMiles();
    }
    
    return (double) Math.round(totalMiles * 100) / 100;
  }
}
