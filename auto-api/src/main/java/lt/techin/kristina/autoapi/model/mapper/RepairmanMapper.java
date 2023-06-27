package lt.techin.kristina.autoapi.model.mapper;

import lt.techin.kristina.autoapi.model.Repairman;
import lt.techin.kristina.autoapi.model.dto.RepairmanDto;

public class RepairmanMapper {

    public static Repairman toRepairman(RepairmanDto repairmanDto) {

        Repairman repairman = new Repairman();
        repairman.setFirstName(repairmanDto.getFirstName());
        repairman.setLastName(repairmanDto.getLastName());
        repairman.setSpecialization(repairmanDto.getSpecialization());
        repairman.setCity(repairmanDto.getCity());

        return repairman;
    }

    public static RepairmanDto toAutoserviceDto(Repairman repairman) {

        RepairmanDto repairmanDto = new RepairmanDto();
        repairmanDto.setFirstName(repairman.getFirstName());
        repairmanDto.setLastName(repairman.getLastName());
        repairmanDto.setSpecialization(repairman.getSpecialization());
        repairmanDto.setCity(repairman.getCity());

        return repairmanDto;
    }
}
