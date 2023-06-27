package lt.techin.kristina.autoapi.model.mapper;

import lt.techin.kristina.autoapi.model.Autoservice;
import lt.techin.kristina.autoapi.model.dto.AutoserviceDto;

public class AutoserviceMapper {

    public static Autoservice toAutoservice(AutoserviceDto autoserviceDto) {

        Autoservice autoservice = new Autoservice();
        autoservice.setName(autoserviceDto.getName());
        autoservice.setAddress(autoserviceDto.getAddress());
        autoservice.setManager(autoserviceDto.getManager());

        return autoservice;
    }

    public static AutoserviceDto toAutoserviceDto(Autoservice autoservice) {

        AutoserviceDto autoserviceDto = new AutoserviceDto();
        autoserviceDto.setName(autoservice.getName());
        autoserviceDto.setAddress(autoservice.getAddress());
        autoserviceDto.setManager(autoservice.getManager());

        return autoserviceDto;
    }
}
