package lt.techin.kristina.autoapi.repository;

import lt.techin.kristina.autoapi.model.Repairman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairmanRepository extends JpaRepository<Repairman, Long> {

    List<Repairman> findAllByAutoserviceId(Long autoserviceId);
}
