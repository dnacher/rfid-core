package com.software.rfid.core.controller;

import com.software.rfid.core.enums.Logger.Codigo;
import com.software.rfid.core.exceptions.RfidException;
import com.software.rfid.core.persistence.model.Alumno;
import com.software.rfid.core.persistence.model.Curso;
import com.software.rfid.core.service.CursoService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Daniel Nacher 2024-08-05 */
@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

  private final CursoService cursoService;

  public CursoController(CursoService cursoService) {
    this.cursoService = cursoService;
  }

  @GetMapping
  public ResponseEntity<?> getCurso() {
    Map<String, Object> body = new HashMap<>();
    try {
      body.put("message", cursoService.getCursos());
      return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
    } catch (RfidException ex) {
      return ResponseFactory.handleErrorCodes(body, null, ex);
    }
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getCursoById(@PathVariable Long id) {
    Map<String, Object> body = new HashMap<>();
    try {
      Curso curso = cursoService.getCursoById(id);
      body.put("message", curso);
      return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
    } catch (RfidException ex) {
      return ResponseFactory.handleErrorCodes(body, null, ex);
    }
  }

  @PostMapping
  public ResponseEntity<?> saveCurso(@RequestBody Curso curso) {
    Map<String, Object> body = new HashMap<>();
    try {
      if (curso.getId() != null) {
        return ResponseFactory.handleErrorCodes(
            body, Codigo.PERSONA_CON_ID_NO_SE_PUEDE_GUARDAR, null);
      }
      Codigo codigo = cursoService.validarDatos(curso);
      if (Codigo.OK.equals(codigo)) {
        body.put("message", this.cursoService.saveCurso(curso));
        return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
      } else {
        return ResponseFactory.handleErrorCodes(body, codigo, null);
      }
    } catch (RfidException ex) {
      return ResponseFactory.handleErrorCodes(body, null, ex);
    }
  }

  @PutMapping
  public ResponseEntity<?> updateCurso(@RequestBody Curso curso) {
    Map<String, Object> body = new HashMap<>();
    try {
      if (curso.getId() == null) {
        return ResponseFactory.handleErrorCodes(
            body, Codigo.PERSONA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR, null);
      }
      Codigo codigo = cursoService.validarDatos(curso);
      if (Codigo.OK.equals(codigo)) {
        return ResponseEntity.ok().body(this.cursoService.updateCurso(curso));
      } else {
        return ResponseFactory.handleErrorCodes(body, codigo, null);
      }
    } catch (RfidException ex) {
      return ResponseFactory.handleErrorCodes(body, null, ex);
    }
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> deleteCurso(@PathVariable Long id) {
    Map<String, Object> body = new HashMap<>();
    try {
      this.cursoService.deleteCursoById(id);
      return ResponseEntity.ok().body("Curso borrado ID: " + id);
    } catch (RfidException ex) {
      return ResponseFactory.handleErrorCodes(body, null, ex);
    }
  }

  @PostMapping("/{cursoId}/alumnos")
  public ResponseEntity<?> agregarAlumnoACurso(
          @PathVariable Long cursoId, @RequestBody Alumno alumno, @RequestParam boolean reasignar) {
    Map<String, Object> body = new HashMap<>();
    try {
      Curso cursoActualizado = cursoService.agregarAlumnoACurso(cursoId, alumno, reasignar);
      body.put("message", cursoActualizado);
      return ResponseFactory.createResponseEntity(body, "", HttpStatus.OK);
    } catch (RfidException ex) {
      return ResponseFactory.handleErrorCodes(body, null, ex);
    }
  }
}
