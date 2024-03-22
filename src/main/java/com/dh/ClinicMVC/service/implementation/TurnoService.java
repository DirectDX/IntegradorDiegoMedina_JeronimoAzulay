package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.entity.Turno;

import com.dh.ClinicMVC.repository.IPacienteRepository;
import com.dh.ClinicMVC.repository.ITurnosRepository;
import com.dh.ClinicMVC.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private ITurnosRepository turnoRepository;
    private IPacienteRepository pacienteRepository;

    @Autowired
    public TurnoService(ITurnosRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno guardar(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }

    @Override
    public Turno buscarPorId(Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if (turnoOptional.isPresent())
            return turnoOptional.get();
        else {
            return null;
        }
    }

    @Override
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public void actualizar(Turno turno) {
    turnoRepository.save(turno);
    }

    @Override
    public Optional<List<Turno>> findByOdontologoId(Long id) {
        return turnoRepository.findByOdontologoId(id);
    }

    @Override
    public Optional<List<Turno>> findByPacienteId(Long id) {
        return findByPacienteId(id);
    }
}
