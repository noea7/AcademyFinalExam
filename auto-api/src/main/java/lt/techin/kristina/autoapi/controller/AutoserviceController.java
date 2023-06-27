package lt.techin.kristina.autoapi.controller;

import lt.techin.kristina.autoapi.model.Autoservice;
import lt.techin.kristina.autoapi.model.dto.AutoserviceDto;
import lt.techin.kristina.autoapi.model.mapper.AutoserviceMapper;
import lt.techin.kristina.autoapi.service.AutoserviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/autoservice")
public class AutoserviceController {

    @Autowired
    private AutoserviceService autoserviceService;

    @GetMapping
    public List<Autoservice> getAllAutoservices() {
        return autoserviceService.getAll();
    }

    @GetMapping("/{autoserviceId}")
    public Optional<Autoservice> getAutoserviceById(@PathVariable Long autoserviceId) {
        return autoserviceService.getById(autoserviceId);
    }

    @PostMapping
    public ResponseEntity<Autoservice> addAutoservice(@Valid @RequestBody AutoserviceDto autoserviceDto) {
        return ResponseEntity.ok(autoserviceService.addAutoservice(AutoserviceMapper.toAutoservice(autoserviceDto)));
    }

    @PatchMapping("/update/{autoserviceId}")
    public ResponseEntity<Autoservice> updateAutoservice(@PathVariable Long autoserviceId, @Valid @RequestBody AutoserviceDto autoserviceDto) {
        return ResponseEntity.ok(autoserviceService.updateAutoservice(autoserviceId, AutoserviceMapper.toAutoservice(autoserviceDto)));
    }

    @DeleteMapping("/delete/{autoserviceId}")
    public ResponseEntity<Void> deleteAutoservice(@PathVariable Long autoserviceId) {
        autoserviceService.deleteAutoservice(autoserviceId);
        return ResponseEntity.ok().build();
    }

}
