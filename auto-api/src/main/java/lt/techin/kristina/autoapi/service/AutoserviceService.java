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
public class AutoserviceService {

    @Autowired
    private AutoserviceRepository autoserviceRepository;

    @Autowired
    private RepairmanRepository repairmanRepository;


    public Autoservice addAutoservice(Autoservice autoservice) {
        return autoserviceRepository.save(autoservice);
    }

    public Optional<Autoservice> getById(Long autoserviceId) {
        return autoserviceRepository.findById(autoserviceId);
    }

    public List<Autoservice> getAll() {
        return autoserviceRepository.findAll();
    }

    public void deleteAutoservice(Long autoserviceId) {
        Autoservice autoserviceToDelete = autoserviceRepository.findById(autoserviceId)
                .orElseThrow(() -> new AutoserviceValidationException("Autoservice with id " +
                        autoserviceId + "does not exist"));
        List<Repairman> affectedRepairmen = repairmanRepository.findAllByAutoserviceId(autoserviceId);
        if (affectedRepairmen.size() > 0) {
            throw new AutoserviceValidationException
                    ("Autoservice cannot be deleted as it has repairmen. Please remove/update affected repairmen first");
        }
        autoserviceRepository.delete(autoserviceToDelete);
    }

    public Autoservice updateAutoservice(Long autoserviceId, Autoservice autoservice) {
        Autoservice autoserviceToUpdate = autoserviceRepository.findById(autoserviceId)
                .orElseThrow(() -> new AutoserviceValidationException("Autoservice with id " +
                        autoserviceId + "does not exist"));
        if (!autoserviceToUpdate.getName().equals(autoservice.getName())) {
            autoserviceToUpdate.setName(autoservice.getName());
        }
        if (!autoserviceToUpdate.getAddress().equals(autoservice.getAddress())) {
            autoserviceToUpdate.setAddress(autoservice.getAddress());
        }
        if (!autoserviceToUpdate.getManager().equals(autoservice.getManager())) {
            autoserviceToUpdate.setManager(autoservice.getManager());
        }
        return autoserviceRepository.save(autoserviceToUpdate);
    }
}
