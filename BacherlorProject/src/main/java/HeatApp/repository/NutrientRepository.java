package HeatApp.repository;

import HeatApp.model.Nutrient;
import org.springframework.data.repository.CrudRepository;

public interface NutrientRepository extends CrudRepository<Nutrient, Integer> {
}
