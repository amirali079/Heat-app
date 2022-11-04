package HeatApp.repository;

import HeatApp.model.UserPreference;
import org.springframework.data.repository.CrudRepository;

public interface UserPreferenceRepository extends CrudRepository<UserPreference, Integer> {
}
