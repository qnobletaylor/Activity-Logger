package edu.bhcc.HW8;

import org.springframework.data.repository.CrudRepository;

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
}
