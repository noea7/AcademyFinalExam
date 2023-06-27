package lt.techin.kristina.autoapi.service;

import lt.techin.kristina.autoapi.exception.AutoserviceValidationException;
import lt.techin.kristina.autoapi.model.Autoservice;
import lt.techin.kristina.autoapi.model.Repairman;
import lt.techin.kristina.autoapi.repository.AutoserviceRepository;
import lt.techin.kristina.autoapi.repository.RepairmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairmanService {

    @Autowired
    RepairmanRepository repairmanRepository;

    @Autowired
    private AutoserviceRepository autoserviceRepository;

    public List<Repairman> getAll() {
        return repairmanRepository.findAll();
    }

    public Optional<Repairman> getById(Long repairmanId) {
        return repairmanRepository.findById(repairmanId);
    }

    public Repairman addRepairman(Long autoserviceId, Repairman repairman) {
        Autoservice autoservice = autoserviceRepository.findById(autoserviceId)
                .orElseThrow(() -> new AutoserviceValidationException("Autoservice with id " +
                        autoserviceId + "does not exist"));
        repairman.setAutoservice(autoservice);
        return repairmanRepository.save(repairman);
    }

    public Repairman updateRepairman(Long repairmanId, Long autoserviceId, Repairman repairman) {

        Repairman repairmanToUpdate = repairmanRepository.findById(repairmanId)
                .orElseThrow(() -> new AutoserviceValidationException("Repairman with id " +
                        repairmanId + "does not exist"));

        if (autoserviceId != null) {
            Autoservice autoservice = autoserviceRepository.findById(autoserviceId)
                    .orElseThrow(() -> new AutoserviceValidationException("Autoservice with id " +
                            autoserviceId + "does not exist"));
            if (!repairmanToUpdate.getAutoservice().equals(autoservice)) {
                repairmanToUpdate.setAutoservice(autoservice);
            }
        }

        if (repairman.getFirstName() != null && !repairman.getFirstName().equals("")
                && !repairmanToUpdate.getFirstName().equals(repairman.getFirstName())) {
            repairmanToUpdate.setFirstName(repairman.getFirstName());
        }
        if (repairman.getLastName() != null && !repairman.getLastName().equals("")
                && !repairmanToUpdate.getLastName().equals(repairman.getLastName())) {
            repairmanToUpdate.setLastName(repairman.getLastName());
        }
        if (repairman.getSpecialization() != null && !repairman.getSpecialization().equals("")
                && !repairmanToUpdate.getSpecialization().equals(repairman.getSpecialization())) {
            repairmanToUpdate.setSpecialization(repairman.getSpecialization());
        }
        if (repairman.getCity() != null && !repairman.getCity().equals("")
                && !repairmanToUpdate.getCity().equals(repairman.getCity())) {
            repairmanToUpdate.setCity(repairman.getCity());
        }

        return repairmanRepository.save(repairmanToUpdate);
    }

    public void deleteRepairman(Long repairmanId) {
        Repairman repairmanToDelete = repairmanRepository.findById(repairmanId)
                .orElseThrow(() -> new AutoserviceValidationException("Repairman with id " +
                        repairmanId + "does not exist"));
        repairmanRepository.delete(repairmanToDelete);
    }
}
