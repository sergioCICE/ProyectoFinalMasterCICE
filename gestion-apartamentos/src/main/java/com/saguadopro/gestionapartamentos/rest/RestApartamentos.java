package com.saguadopro.gestionapartamentos.rest;

import com.saguadopro.gestionapartamentos.rest.dto.ApartamentoDTO;
import com.saguadopro.gestionapartamentos.rest.dto.HuespedDTO;
import com.saguadopro.gestionapartamentos.rest.dto.PropietarioDTO;
import com.saguadopro.gestionapartamentos.rest.dto.CapacidadDTO;
import com.saguadopro.gestionapartamentos.services.ApartamentosService;
import com.saguadopro.gestionapartamentos.services.HuespedService;
import com.saguadopro.gestionapartamentos.services.PropietariosService;
import com.saguadopro.gestionapartamentos.services.CapacidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase Controladora los endpoints de la gestion de los apartamentos
 * @see {@link ApartamentosService}
 * @see {@link CapacidadService}
 * @see {@link HuespedService}
 * @see {@link PropietariosService}
 */
@RestController
public class RestApartamentos {

    @Autowired
    private ApartamentosService apartamentosService;

    @Autowired
    private PropietariosService propietariosService;

    @Autowired
    private CapacidadService tipoModelosService;

    @Autowired
    private HuespedService huespedService;


    @RequestMapping(value = "/apartamentos",method = RequestMethod.POST)
    public HttpStatus crearApartamento(@RequestBody ApartamentoDTO apartamentoDTO){   // para probar con el postman @RequestBody
        if (apartamentosService.crearApartamento(apartamentoDTO)){
            return HttpStatus.CREATED;
        }else{
            return HttpStatus.BAD_REQUEST;
        }
    }

    @RequestMapping(value = "/apartamentos/{idApartamento}",method = RequestMethod.DELETE)
    public Boolean eliminarApartamentos(@PathVariable(value = "idApartamento") String idApartamento){
        if (apartamentosService.eliminarApartamento(Long.parseLong(idApartamento))){
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping(value = "/apartamentos/modificar",method = RequestMethod.PUT)
    public HttpStatus modificarApartamento(@RequestBody ApartamentoDTO apartamentoDTOModificado){
        if (apartamentosService.modificarApartamento(apartamentoDTOModificado)){
            return HttpStatus.OK;
        }else{
            return HttpStatus.BAD_REQUEST;
        }
    }

    @RequestMapping(value = "/apartamentos/{idApartamento}", method = RequestMethod.GET)
    public List<ApartamentoDTO> buscarApartamento(@PathVariable(value = "idApartamento") String idApartamento){
        return apartamentosService.buscarApartamento(Long.parseLong(idApartamento));
    }

    @RequestMapping(value = "/apartamentos", method = RequestMethod.GET)
    public List<ApartamentoDTO> listaApartamentos(){
        return apartamentosService.listaApartamentos();
    }

    @RequestMapping(value = "/apartamentos/propietarios", method = RequestMethod.GET)
    public List<PropietarioDTO> listaPropietarios(){
        return propietariosService.listaPropietarios();
    }


    @RequestMapping(value = "/apartamentos/propietarios/{idPropietario}", method = RequestMethod.GET)
    public PropietarioDTO buscarPropietario(@PathVariable(value = "idPropietario") String idPropietario){
        return propietariosService.buscarPropietario(Integer.parseInt(idPropietario));
    }

    @RequestMapping(value = "/apartamentos/capacidades", method = RequestMethod.GET)
    public List<CapacidadDTO> listaCapacidad(){
        return tipoModelosService.listaCapacidades();
    }


    @RequestMapping(value = "/apartamentos/capacidades/{capacidad}", method = RequestMethod.GET)
    public CapacidadDTO buscarPorCapacidad(@PathVariable(value = "capacidad") String capacidad){
        return tipoModelosService.buscarPorCapacidad(Integer.parseInt(capacidad));
    }

    @RequestMapping(value = "/apartamentos/huesped/{idHuesped}", method = RequestMethod.GET)
    public HuespedDTO buscarHuesped(@PathVariable(value = "idHuesped") String idHuesped){
        if (idHuesped.equals("")){
            return null;
        }else {
            return huespedService.buscarHuesped(Long.parseLong(idHuesped));
        }
    }

}
