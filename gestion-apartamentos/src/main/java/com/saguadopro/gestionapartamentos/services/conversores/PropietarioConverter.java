package com.saguadopro.gestionapartamentos.services.conversores;

import com.saguadopro.gestionapartamentos.entities.Propietario;
import com.saguadopro.gestionapartamentos.rest.dto.PropietarioDTO;
import org.springframework.stereotype.Service;

@Service
public class PropietarioConverter {

    public static PropietarioDTO propietarioToDto(Propietario propietario) {
        PropietarioDTO propietarioDTO = new PropietarioDTO();
        propietarioDTO.setIdPropietario(propietario.getIdPropietario());
        propietarioDTO.setNombre(propietario.getNombre());
        propietarioDTO.setApellidos(propietario.getApellidos());
        propietarioDTO.setTelefono(propietario.getTelefono());
        propietarioDTO.setEmail(propietario.getEmail());
        return propietarioDTO;
    }

    public static Propietario dtoToPropietario(PropietarioDTO propietarioDTO) {
        Propietario propietario = new Propietario();
        propietario.setIdPropietario(propietarioDTO.getIdPropietario());
        propietario.setNombre(propietarioDTO.getNombre());
        propietario.setApellidos(propietarioDTO.getApellidos());
        propietario.setTelefono(propietarioDTO.getTelefono());
        propietario.setEmail(propietarioDTO.getEmail());
        return propietario;
    }
}