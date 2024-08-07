package com.software.rfid.core.service;

import com.software.rfid.core.enums.Logger.Codigo;
import com.software.rfid.core.exceptions.NotFoundException;
import com.software.rfid.core.exceptions.UpdateException;
import com.software.rfid.core.persistence.model.Alumno;
import com.software.rfid.core.persistence.model.Clase;
import com.software.rfid.core.persistence.repository.ClaseRepository;
import java.util.List;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/** Daniel Nacher 2024-08-05 */
@Service
public class ClaseService {

  private final ClaseRepository claseRepository;

  public ClaseService(ClaseRepository claseRepository) {
    this.claseRepository = claseRepository;
  }

  public List<Clase> getClases() {
    return claseRepository.findAll();
  }

  public Clase getClaseById(Long id) {
    return claseRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(HttpStatus.OK, "No se encontro la clase"));
  }

  public Clase saveClase(Clase clase) {
    return claseRepository.save(clase);
  }

  public Clase updateClase(Clase clase) {
    if (clase.getId() != null) {
      return claseRepository.save(clase);
    } else {
      throw new UpdateException("No se puede actualizar la clase sin Id");
    }
  }

  public void deleteClaseById(Long id) {
    claseRepository.deleteById(id);
  }

  public Codigo validarDatos(Clase clase) {
    if (StringUtil.isEmpty(clase.getNombre())) {
      return Codigo.FALTA_NOMBRE;
    }
    return Codigo.OK;
  }
}
