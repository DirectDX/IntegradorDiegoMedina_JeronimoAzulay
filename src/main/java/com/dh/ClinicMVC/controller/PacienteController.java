package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.model.Paciente;
import com.dh.ClinicMVC.service.IPacienteService;
import com.dh.ClinicMVC.service.implementation.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")

public class PacienteController {
    private IPacienteService pacienteService;

    public  PacienteController (PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    @PostMapping
    public Paciente guardar(@RequestBody Paciente paciente) {
        return pacienteService.guardar(paciente);
    }
    @GetMapping("/all")
    public List<Paciente> listarTodos() {
        return pacienteService.listarTodos();
    }
    @GetMapping
    public Paciente buscarPacientePorId(@RequestParam("id") Integer id) {
        return pacienteService.buscarPorId(id);
    }
}
