package com.saguadopro.conversorms.services;

import com.saguadopro.conversorms.entities.*;
import com.saguadopro.conversorms.feign.FotosFeign;
import com.saguadopro.conversorms.rest.dto.*;
import com.saguadopro.conversorms.services.impl.DtoToEntityImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de convertir Objetos DTO en Entidades
 */
@Service
public class DtoToEntityService implements DtoToEntityImpl {

    private static Logger logger = Logger.getLogger(EntityToDtoService.class);

    @Autowired
    private FotosFeign fotosFeign;

    /**
     * Convierte un objeto de tipo Usuario de Dto (@see {@link UsuarioDTO} a Entidad (@see {@link Usuario}))
     * @param usuarioDTO - Dto de tipo Usuario
     * @return - Entidad de tipo Usuario
     */
    public  Usuario usuarioDtoToEntity(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        try {
            usuario.setIdUsuario(usuarioDTO.getIdUsuario());
            usuario.setUsername(usuarioDTO.getUsername());
            usuario.setPerfil(perfilDtoToPerfil(usuarioDTO.getPerfil()));
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setApellidos(usuarioDTO.getApellidos());
            usuario.setPasswd(usuarioDTO.getPasswd());
            if (!usuarioDTO.getFoto().isEmpty() || !usuarioDTO.getFoto().equals("")){
                usuario.setFoto_url(fotosFeign.guardarFoto(usuarioDTO.getUsername(),
                        fotosFeign.decodificarFoto(usuarioDTO.getFoto()),"usuario"));
            }else {
                usuario.setFoto_url("src/main/resources/fotos/logo_user_off.png");
            }
        }catch (Exception e){
            logger.error("No se ha podido convertir una DTO Usuario a Entidad: "+e.getMessage());
        }
        return usuario;
    }

    /**
     * Convierte un objeto de tipo Perfil de Dto (@see {@link Perfil} a Entidad (@see {@link PerfilDTO}))
     * @param perfilDTO - Dto de tipo Perfil
     * @return - Entidad de tipo Perfil
     */
    public Perfil perfilDtoToPerfil(PerfilDTO perfilDTO){
        Perfil perfil = new Perfil();
        try {
            perfil.setIdPerfil(perfilDTO.getIdPerfil());
            perfil.setNombrePerfil(perfilDTO.getNombrePerfil());
        }catch (Exception e){
            logger.error("No se ha podido convertir una DTO Perfil a Entidad: "+e.getMessage());
        }
        return perfil;
    }

    /**
     * Convierte un objeto de tipo Apartamento de Dto (@see {@link ApartamentoDTO} a Entidad (@see {@link Apartamento}))
     * @param apartamentoDTO - Dto de tipo Apartamento
     * @return - Entidad de tipo Apartamento
     */
    @Override
    public Apartamento apartamentoDtoToEntity(ApartamentoDTO apartamentoDTO) {
        Apartamento apartamento = new Apartamento();
        try {
            apartamento.setPuerta(apartamentoDTO.getPuerta());
            apartamento.setPiso(apartamentoDTO.getPiso());
            apartamento.setPropietario(propietarioDtoToEntity(apartamentoDTO.getPropietario()));
            apartamento.setDireccion(apartamentoDTO.getDireccion());
            apartamento.setCapacidad(capacidadDtoToEntity(apartamentoDTO.getCapacidad()));
            apartamento.setIdApartamento(apartamentoDTO.getIdApartamento());
            if (apartamentoDTO.getHuesped() != null)apartamento.setHuesped(huespedDtoToEntity(apartamentoDTO.getHuesped()));
            if (!apartamentoDTO.getFoto().isEmpty() || !apartamentoDTO.getFoto().equals("")){
                apartamento.setFoto_url(fotosFeign.guardarFoto(String.valueOf(apartamentoDTO.getIdApartamento()),
                        fotosFeign.decodificarFoto(apartamentoDTO.getFoto()),"apartamento"));
            }else {
                apartamento.setFoto_url("src/main/resources/fotos/logo_apartamento.png");
            }
        }catch (Exception e){
            logger.error("No se ha podido convertir una DTO Apartamento a Entidad: "+e.getMessage());
        }
        return apartamento;
    }

    /**
     * Convierte un objeto de tipo Capacidad de Dto (@see {@link CapacidadDTO} a Entidad (@see {@link Capacidad}))
     * @param capacidadDTO - Dto de tipo Apartamento
     * @return - Entidad de tipo Apartamento
     */
    @Override
    public Capacidad capacidadDtoToEntity(CapacidadDTO capacidadDTO) {
        Capacidad capacidad = new Capacidad();
        try {
            capacidad.setIdCapacidad(capacidadDTO.getIdCapacidad());
            capacidad.setMaxPersonas(capacidadDTO.getMaxPersonas());
        }catch (Exception e){
            logger.error("No se ha podido convertir una DTO Capacidad a Entidad: "+e.getMessage());
        }
        return capacidad;
    }

    /**
     * Convierte un objeto de tipo Huesped de Dto (@see {@link HuespedDTO} a Entidad (@see {@link Huesped}))
     * @param huespedDTO - Dto de tipo Huesped
     * @return - Entidad de tipo Huesped
     */
    @Override
    public Huesped huespedDtoToEntity(HuespedDTO huespedDTO) {
        Huesped huesped = new Huesped();
        try {
            huesped.setIdHuesped(huespedDTO.getIdHuesped());
            huesped.setNombre(huespedDTO.getNombre());
            huesped.setApellidos(huespedDTO.getApellidos());
            huesped.setTelefono(huespedDTO.getTelefono());
            huesped.setEmail(huespedDTO.getEmail());
            huesped.setDni(huespedDTO.getDni());
        }catch (Exception e){
            logger.error("No se ha podido convertir una DTO Huesped a Entidad: "+e.getMessage());
        }
        return huesped;
    }

    /**
     * Convierte un objeto de tipo Propietario de Dto (@see {@link PropietarioDTO} a Entidad (@see {@link Propietario}))
     * @param propietarioDTO - Dto de tipo Propietario
     * @return - Entidad de tipo Propietario
     */
    @Override
    public Propietario propietarioDtoToEntity(PropietarioDTO propietarioDTO) {
        Propietario propietario = new Propietario();
        try {
            propietario.setIdPropietario(propietarioDTO.getIdPropietario());
            propietario.setNombre(propietarioDTO.getNombre());
            propietario.setApellidos(propietarioDTO.getApellidos());
            propietario.setTelefono(propietarioDTO.getTelefono());
            propietario.setEmail(propietarioDTO.getEmail());
        }catch (Exception e){
            logger.error("No se ha podido convertir una DTO Propietario a Entidad: "+e.getMessage());
        }
        return propietario;
    }

    /**
     * Convierte un objeto de tipo Reserva de Dto (@see {@link ReservaDTO} a Entidad (@see {@link Reserva}))
     * @param reservaDTO - Dto de tipo Reserva
     * @return - Entidad de tipo Reserva
     */
    @Override
    public Reserva reservaDtoToEntity(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        try {
            reserva.setIdReserva(reservaDTO.getIdReserva());
            reserva.setCliente(reservaDTO.getCliente());
            reserva.setFechaEntrada(reservaDTO.getFechaEntrada());
            reserva.setFechaSalida(reservaDTO.getFechaSalida());
            reserva.setIdApartamento(reservaDTO.getIdApartamento());
            reserva.setNumero_personas(reservaDTO.getCapacidad());
            reserva.setTieneParking(reservaDTO.getTieneParking());
            reserva.setPrecioTotal(reservaDTO.getPrecioTotal());
            reserva.setEstaAsignada(reservaDTO.getEstaAsignada());
        }catch (Exception e){
            logger.error("No se ha podido convertir una DTO Reserva a Entidad: "+e.getMessage());
        }
        return reserva;
    }

}
