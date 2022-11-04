package HeatApp.repository;

import HeatApp.model.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food,Integer> {
}
