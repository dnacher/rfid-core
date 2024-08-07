package com.software.rfid.core.controller;

import com.software.rfid.core.enums.Logger.Codigo;
import com.software.rfid.core.exceptions.RfidException;
import com.software.rfid.core.persistence.model.Clase;
import com.software.rfid.core.service.ClaseService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Daniel Nacher 2024-08-05 */
@RestController
@RequestMapping("/api/v1/clases")
public class ClaseController {

    private final ClaseService claseService;

    public ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getClase() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", claseService.getClases());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getClaseById(@PathVariable Long id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", claseService.getClaseById(id));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveClase(@RequestBody Clase clase) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(clase.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.CLASE_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = claseService.validarDatos(clase);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", this.claseService.saveClase(clase));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "")
    public ResponseEntity<?> updateClase(@RequestBody Clase clase) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(clase.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.CLASE_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = claseService.validarDatos(clase);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(this.claseService.updateClase(clase));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (RfidException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteClase(@PathVariable Long id) {
        Map<String, Object> body = new HashMap<>();
        try{
            this.claseService.deleteClaseById(id);
            return ResponseEntity.ok().body("Clase borrada ID: " + id);
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
