package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.model.Domicilio;
import com.dh.ClinicMVC.model.Odontologo;
import com.dh.ClinicMVC.service.IDomicilioService;

import com.dh.ClinicMVC.service.implementation.DomicilioService;
import com.dh.ClinicMVC.service.implementation.OdontologoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    private IDomicilioService domicilioService;

    public DomicilioController(DomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }
    @PostMapping
    public Domicilio guardar(@RequestBody Domicilio domicilio) {
        return domicilioService.guardar(domicilio);
    }
    @GetMapping("/all")
    public List<Domicilio> listarTodos() {
        return domicilioService.listarTodos();
    }
    @GetMapping
    public Domicilio buscarPacientePorId(@RequestParam("id") Integer id) {
        return domicilioService.buscarPorId(id);
    }
    @DeleteMapping
    public void eliminar(@RequestParam("id") Integer id) {
        domicilioService.eliminar(id);
    }
}
