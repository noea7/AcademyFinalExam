package lt.techin.kristina.autoapi.controller;

import lt.techin.kristina.autoapi.model.Rating;
import lt.techin.kristina.autoapi.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rating")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @GetMapping("/{repairmanId}")
    public Double getRepairmanRating(@PathVariable Long repairmanId) {
        return ratingService.getRepairmanRating(repairmanId);
    }

    @PostMapping("/{repairmanId}/{userId}/{value}")
    public ResponseEntity<Rating> addRating(@PathVariable(value = "repairmanId") Long repairmanId,
                                            @PathVariable(value = "userId") Long userId,
                                            @PathVariable(value = "value") int value) {
        return ResponseEntity.ok(ratingService.addRating(repairmanId, userId, value));
    }
}
