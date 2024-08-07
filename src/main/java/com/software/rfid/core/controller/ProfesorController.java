package com.software.rfid.core.controller;

import com.software.rfid.core.enums.Logger.Codigo;
import com.software.rfid.core.exceptions.RfidException;
import com.software.rfid.core.persistence.model.Profesor;
import com.software.rfid.core.service.ProfesorService;
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
@RequestMapping("/api/v1/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getProfesores() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", profesorService.getProfesores());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProfesorById(@PathVariable Long id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", profesorService.getProfesorById(id));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveProfesor(@RequestBody Profesor profesor) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(profesor.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.PERSONA_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = profesorService.validarDatos(profesor);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", this.profesorService.saveProfesor(profesor));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "")
    public ResponseEntity<?> updateProfesor(@RequestBody Profesor profesor) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(profesor.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.PERSONA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = profesorService.validarDatos(profesor);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(this.profesorService.updateProfesor(profesor));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (RfidException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProfesor(@PathVariable Long id) {
        Map<String, Object> body = new HashMap<>();
        try{
            this.profesorService.deleteProfesorById(id);
            return ResponseEntity.ok().body("Profesor borrado ID: " + id);
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
