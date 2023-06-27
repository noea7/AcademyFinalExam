package lt.techin.kristina.autoapi.controller;

import lt.techin.kristina.autoapi.model.Repairman;
import lt.techin.kristina.autoapi.model.dto.RepairmanDto;
import lt.techin.kristina.autoapi.model.mapper.RepairmanMapper;
import lt.techin.kristina.autoapi.service.RepairmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/repairman")
public class RepairmanController {

    @Autowired
    RepairmanService repairmanService;

    @GetMapping
    public List<Repairman> getAllRepairmen() {
        return repairmanService.getAll();
    }

    @GetMapping("/{repairmanId}")
    public Optional<Repairman> getRepairmanById(@PathVariable Long repairmanId) {
        return repairmanService.getById(repairmanId);
    }

    @PostMapping("/{autoserviceId}")
    public ResponseEntity<Repairman> addRepairman(@PathVariable Long autoserviceId,
                                                  @Valid @RequestBody RepairmanDto repairmanDto) {
        return ResponseEntity.ok(repairmanService.addRepairman(autoserviceId, RepairmanMapper
                .toRepairman(repairmanDto)));
    }

    @PatchMapping("/update/{repairmanId}/{autoserviceId}")
    public ResponseEntity<Repairman> updateRepairman(@PathVariable(value = "repairmanId") Long repairmanId, @PathVariable(value = "autoserviceId", required = false) Long autoserviceId,
                                                     @Valid @RequestBody RepairmanDto repairmanDto) {
        return ResponseEntity.ok(repairmanService.updateRepairman(repairmanId, autoserviceId, RepairmanMapper
                .toRepairman(repairmanDto)));
    }

    @DeleteMapping("/delete/{repairmanId}")
    public ResponseEntity<Void> deleteRepairman(@PathVariable Long repairmanId) {
        repairmanService.deleteRepairman(repairmanId);
        return ResponseEntity.ok().build();
    }
}
