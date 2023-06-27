package lt.techin.kristina.autoapi.repository;

import lt.techin.kristina.autoapi.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findAllByRepairmanId(Long repairmanId);

    boolean existsByRepairmanIdAndUserId(Long repairmanId, Long userId);
}
