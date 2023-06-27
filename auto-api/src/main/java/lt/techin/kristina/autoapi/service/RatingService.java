package lt.techin.kristina.autoapi.service;

import lt.techin.kristina.autoapi.exception.AutoserviceValidationException;
import lt.techin.kristina.autoapi.model.Rating;
import lt.techin.kristina.autoapi.model.Repairman;
import lt.techin.kristina.autoapi.model.User;
import lt.techin.kristina.autoapi.repository.RatingRepository;
import lt.techin.kristina.autoapi.repository.RepairmanRepository;
import lt.techin.kristina.autoapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RepairmanRepository repairmanRepository;


    public Double getRepairmanRating(Long repairmanId) {
        if (!repairmanRepository.existsById(repairmanId)) {
            throw new AutoserviceValidationException("Repairman with id " +
                    repairmanId + "does not exist");
        }
        return ratingRepository.findAllByRepairmanId(repairmanId).stream().mapToInt(Rating::getRatingValue)
                .average().orElse(0);
    }

    public Rating addRating(Long repairmanId, Long userId, int value) {
        if (value < 0 || value > 10) {
            throw new AutoserviceValidationException("Invalid rating");
        }
        Repairman repairman = repairmanRepository.findById(repairmanId)
                .orElseThrow(() -> new AutoserviceValidationException("Repairman with id " +
                        repairmanId + "does not exist"));
        User user = userRepository.findById(userId).orElseThrow(
                () -> new AutoserviceValidationException("User with id " +
                userId + "does not exist"));
        if (ratingRepository.existsByRepairmanIdAndUserId(repairmanId, userId)) {
            throw new AutoserviceValidationException("Rating already submitted");
        }
        Rating rating = new Rating();
        rating.setRatingValue(value);
        rating.setRepairman(repairman);
        rating.setUser(user);
        return ratingRepository.save(rating);
    }
}
