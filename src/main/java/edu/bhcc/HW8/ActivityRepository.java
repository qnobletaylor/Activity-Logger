package edu.bhcc.HW8;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * ActivityRepository interface for JPA to be able to create/read/delete from database.
 */
public interface ActivityRepository extends CrudRepository<Activity, Long> {

  /**
   * Finds an Activity by ID.
   *
   * @param id the activity to find
   * @return the activity if found
   */
  Activity findById(long id);

  /**
   * Finds all Activities that have been done on a given route.
   *
   * @param route the route to search for
   * @return a list of all activities for that route
   */
  List<Activity> findByRoute(String route);
}
