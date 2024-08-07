package com.software.rfid.core.service;

import com.software.rfid.core.enums.Logger.Codigo;
import com.software.rfid.core.exceptions.NotFoundException;
import com.software.rfid.core.exceptions.UpdateException;
import com.software.rfid.core.persistence.model.Materia;
import com.software.rfid.core.persistence.repository.MateriaRepository;
import java.util.List;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/** Daniel Nacher 2024-08-05 */
@Service
public class MateriaService {

  private final MateriaRepository materiaRepository;

  public MateriaService(MateriaRepository materiaRepository) {
    this.materiaRepository = materiaRepository;
  }

  public List<Materia> getMaterias() {
    return materiaRepository.findAll();
  }

  public Materia getMateriaById(Long id) {
    return materiaRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(HttpStatus.OK, "No se encontro el materia"));
  }

  public Materia saveMateria(Materia materia) {
    return materiaRepository.save(materia);
  }

  public Materia updateMateria(Materia materia) {
    if (materia.getId() != null) {
      return materiaRepository.save(materia);
    } else {
      throw new UpdateException("No se puede actualizar la materia sin Id");
    }
  }

  public void deleteMateriaById(Long id) {
    materiaRepository.deleteById(id);
  }

  public Codigo validarDatos(Materia materia) {
    if (StringUtil.isEmpty(materia.getNombre())) {
      return Codigo.FALTA_NOMBRE;
    }
    return Codigo.OK;
  }
}
