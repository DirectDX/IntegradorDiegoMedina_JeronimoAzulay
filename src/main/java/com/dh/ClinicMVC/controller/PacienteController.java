package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) throws Exception {
        Paciente nuevoPaciente = pacienteService.guardar(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        List<Paciente> listaPacientes = pacienteService.listarTodos();
            return ResponseEntity.ok(listaPacientes);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        Optional<Paciente> pacienteOptional = pacienteService.buscarPorId(id);
        return pacienteOptional.isPresent() ? ResponseEntity.ok(pacienteOptional.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        ResponseEntity<String> response;
        Optional<Paciente> pacienteOptional = pacienteService.buscarPorId(id);
        if (pacienteOptional.isPresent()) {
            pacienteService.eliminar(id);
            response = ResponseEntity.ok("Se eliminó el paciente con id " + id);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @PutMapping()
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente) throws Exception {
        ResponseEntity<Paciente> response;
        Optional<Paciente> pacienteOptional = pacienteService.buscarPorId(paciente.getId());
        if (pacienteOptional.isPresent()) {
            pacienteService.actualizar(paciente);
            response = new ResponseEntity(paciente, HttpStatus.OK);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }
}