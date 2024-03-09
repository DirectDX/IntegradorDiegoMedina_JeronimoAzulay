package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.model.Paciente;
import com.dh.ClinicMVC.service.IPacienteService;
import com.dh.ClinicMVC.service.implementation.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/paciente")
public class IndexPacienteController {

    private IPacienteService pacienteService;
    public IndexPacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    @GetMapping()
    public String buscarPacientePorId(Model model, @RequestParam("id") Integer id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        model.addAttribute("dni", paciente.getDni());
        model.addAttribute("fechaIngreso", paciente.getFechaIngreso());
        model.addAttribute("domicilio", paciente.getDomicilio().getId());
        return "buscarPaciente";
    }

}
