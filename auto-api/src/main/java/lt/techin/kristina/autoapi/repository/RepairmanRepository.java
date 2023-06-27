package lt.techin.kristina.autoapi.repository;

import lt.techin.kristina.autoapi.model.Repairman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairmanRepository extends JpaRepository<Repairman, Long> {
}
