package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.model.Odontologo;
import com.dh.ClinicMVC.service.IOdontologoService;
import com.dh.ClinicMVC.service.implementation.OdontologoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")

public class OdontologoController {
    private IOdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    @PostMapping
    public Odontologo guardar(@RequestBody Odontologo odontologo) {
        return odontologoService.guardar(odontologo);
    }
    @GetMapping("/all")
    public List<Odontologo> listarTodos() {
        return odontologoService.listarTodos();
    }
    @GetMapping
    public Odontologo buscarPacientePorId(@RequestParam("id") Integer id) {
        return odontologoService.buscarPorId(id);
    }
    @DeleteMapping
    public void eliminar(@RequestParam("id") Integer id) {
        odontologoService.eliminar(id);
    }
}
