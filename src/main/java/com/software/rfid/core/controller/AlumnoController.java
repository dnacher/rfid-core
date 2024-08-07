package com.software.rfid.core.controller;

import com.software.rfid.core.enums.Logger.Codigo;
import com.software.rfid.core.exceptions.RfidException;
import com.software.rfid.core.persistence.model.Alumno;
import com.software.rfid.core.service.AlumnoService;
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

import java.util.HashMap;
import java.util.Map;

/** Daniel Nacher 2024-08-05 */
@RestController
@RequestMapping("/api/v1/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getAlumno() {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", alumnoService.getAlumnos());
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAlumnoById(@PathVariable Long id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", alumnoService.getAlumnoById(id));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveAlumno(@RequestBody Alumno alumno) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(alumno.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.PERSONA_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = alumnoService.validarDatos(alumno);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", this.alumnoService.saveAlumno(alumno));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "")
    public ResponseEntity<?> updateAlumno(@RequestBody Alumno alumno) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(alumno.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.PERSONA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = alumnoService.validarDatos(alumno);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(this.alumnoService.updateAlumno(alumno));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (RfidException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAlumno(@PathVariable Long id) {
        Map<String, Object> body = new HashMap<>();
        try{
            this.alumnoService.deleteAlumnoById(id);
            return ResponseEntity.ok().body("Alumno borrado ID: " + id);
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
