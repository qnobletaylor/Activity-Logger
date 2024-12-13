package edu.bhcc.HW8;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ActivityController {
  private ActivityRepository repo;

  public ActivityController(ActivityRepository repo) {
    this.repo = repo;
  }

  /**
   * Maps to endpoint '/' to index.html
   */
  @GetMapping("/")
  public String index(Model model) {
    List<Activity> activityList = getAllActivities();
    model.addAttribute("activity_list", activityList);
    return "index";
  }

  @GetMapping("/add_activity")
  public String addActivity(String route, Double miles, String date, Model model) {
    Activity activity = new Activity(route, miles, date);
    repo.save(activity);
    List<Activity> activityList = getAllActivities();
    model.addAttribute("toast", "New activity added: " + route + "!");
    model.addAttribute("add", true);
    model.addAttribute("activity_list", activityList);
    return "index";
  }

  @GetMapping("/delete_activity")
  public String deleteActivity(Integer id, Model model) {
    Activity activity = repo.findById(id);
    if (activity != null) {
      model.addAttribute("toast", "Deleted activity: " + activity.getRoute() + " | " + activity.getDate());
      model.addAttribute("delete", true);
      repo.delete(activity);
    } else {
      model.addAttribute("toast", "Could not find activity with ID: " + id + ".");
      model.addAttribute("error", true);
    }

    List<Activity> activityList = getAllActivities();
    model.addAttribute("activity_list", activityList);
    return "index";
  }

  @GetMapping("/update_activity")
  public String updateActivity(Integer id, String route, Double miles, String date, Model model) {
    Activity activity = repo.findById(id);
    String oldRoute = activity.getRoute();
    if (activity != null) {
      activity.setRoute(route);
      activity.setMiles(miles);
      activity.setDate(date);
      repo.save(activity);

      model.addAttribute("toast", "Updated Activity: " + oldRoute + " --> " + route + "!");
      model.addAttribute("update", true);
    } else {
      model.addAttribute("toast", "Could not find activity with ID: " + id + ".");
      model.addAttribute("error", true);
    }

    List<Activity> activityList = getAllActivities();
    model.addAttribute("activity_list", activityList);
    return "index";
  }

  private List<Activity> getAllActivities() {
    Iterable<Activity> activityIter = repo.findAll();
    List<Activity> activityList = new ArrayList<Activity>();
    for (Activity activity : activityIter) {
      activityList.add(activity);
    }
    return activityList;
  }
}
