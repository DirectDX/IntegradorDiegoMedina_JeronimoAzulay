package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.entity.Turno;
import com.dh.ClinicMVC.service.IOdontologoService;
import com.dh.ClinicMVC.service.IPacienteService;
import com.dh.ClinicMVC.service.ITurnoService;
import com.dh.ClinicMVC.service.implementation.OdontologoService;
import com.dh.ClinicMVC.service.implementation.PacienteService;
import com.dh.ClinicMVC.service.implementation.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private static final Logger LOGGER = Logger.getLogger(TurnoController.class);

    private ITurnoService turnoService;
    private IOdontologoService odontologoService;
    private IPacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }


    //endpoint para guardar un turno
    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        ResponseEntity<Turno> response;

        LOGGER.info("esto trae el turno: " + turno);
//        vamos a chequear que exista el odontologo y la paciente
        if (odontologoService.buscarPorId(turno.getOdontologo().getId()) != null &&
                pacienteService.buscarPorId(turno.getPaciente().getId()) != null) {
            //setear una respuesta en 200 y vamos a hacer que devuelva el turno
            response = ResponseEntity.ok(turnoService.guardar(turno));
        } else {
            //si no existe el odontologo o el paciente
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {
        Turno turno = turnoService.buscarPorId(id);
        return turno != null ? ResponseEntity.ok(turno) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response;
        Turno turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado != null) {
            pacienteService.eliminar(id);
            response = ResponseEntity.ok("Se eliminó el turno con id " + id);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }
    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        List<Turno> listaTurnos = turnoService.listarTodos();
        if (!listaTurnos.isEmpty()) {
            return ResponseEntity.ok(listaTurnos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<Turno>> buscarPorPacienteId(@PathVariable Long id) {
        List<Turno> turnosEncontrados = turnoService.buscarPorPacienteId(id);
        if (!turnosEncontrados.isEmpty()) {
            return ResponseEntity.ok(turnosEncontrados);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/odontolgo/{id}")
    public ResponseEntity<List<Turno>> buscarPorOdontologoId(@PathVariable Long id) {
        List<Turno> turnosEncontrados = turnoService.buscarPorOdontologoId(id);
        if (!turnosEncontrados.isEmpty()) {
            return ResponseEntity.ok(turnosEncontrados);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
