package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.model.Domicilio;
import com.dh.ClinicMVC.service.IDomicilioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    private IDomicilioService domicilioService;

    public DomicilioController(IDomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    @PostMapping
    public ResponseEntity<Domicilio> guardar(@RequestBody Domicilio domicilio) {
        Domicilio nuevoDomicilio = domicilioService.guardar(domicilio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDomicilio);
    }

    @GetMapping
    public ResponseEntity<List<Domicilio>> listarTodos() {
        List<Domicilio> listaDomicilios = domicilioService.listarTodos();
        if (!listaDomicilios.isEmpty()) {
            return ResponseEntity.ok(listaDomicilios);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Domicilio> buscarPorId(@PathVariable Integer id) {
        Domicilio domicilio = domicilioService.buscarPorId(id);
        return domicilio != null ? ResponseEntity.ok(domicilio) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response;
        Domicilio domicilioBuscado = domicilioService.buscarPorId(id);
        if (domicilioBuscado != null) {
            domicilioService.eliminar(id);
            response = ResponseEntity.ok("Se eliminó el domicilio con id " + id);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody Domicilio domicilio) {
        ResponseEntity<String> response;
        Domicilio domicilioBuscado = domicilioService.buscarPorId(id);
        if (domicilioBuscado != null) {
            domicilio.setId(id);
            domicilioService.actualizar(domicilio);
            response = ResponseEntity.ok("Se actualizó el domicilio con id " + id);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }
}