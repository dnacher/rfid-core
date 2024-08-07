package com.software.rfid.core.controller;

import com.software.rfid.core.enums.Logger.Codigo;
import com.software.rfid.core.exceptions.RfidException;
import com.software.rfid.core.persistence.model.Materia;
import com.software.rfid.core.service.MateriaService;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/api/v1/materias")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getMateria() {
        Map<String, Object> body = new HashMap<>();
        try{
            List<Materia> materias = materiaService.getMaterias();
            body.put("message", materias);
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getMateriaById(@PathVariable Long id) {
        Map<String, Object> body = new HashMap<>();
        try{
            body.put("message", materiaService.getMateriaById(id));
            return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveMateria(@RequestBody Materia materia) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(materia.getId()!=null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.MATERIA_CON_ID_NO_SE_PUEDE_GUARDAR, null);
            }
            Codigo codigo = materiaService.validarDatos(materia);
            if(Codigo.OK.equals(codigo)) {
                body.put("message", this.materiaService.saveMateria(materia));
                return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @PutMapping(value = "")
    public ResponseEntity<?> updateMateria(@RequestBody Materia materia) {
        Map<String, Object> body = new HashMap<>();
        try{
            if(materia.getId()==null) {
                return ResponseFactory.handleErrorCodes(body, Codigo.MATERIA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
            }
            Codigo codigo = materiaService.validarDatos(materia);
            if(Codigo.OK.equals(codigo)) {
                return ResponseEntity.ok().body(this.materiaService.updateMateria(materia));
            } else {
                return ResponseFactory.handleErrorCodes(body, codigo, null);
            }
        } catch (RfidException ex) {
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteMateria(@PathVariable Long id) {
        Map<String, Object> body = new HashMap<>();
        try{
            this.materiaService.deleteMateriaById(id);
            return ResponseEntity.ok().body("Materia borrado ID: " + id);
        } catch (RfidException ex){
            return ResponseFactory.handleErrorCodes(body, null, ex);
        }
    }
}
