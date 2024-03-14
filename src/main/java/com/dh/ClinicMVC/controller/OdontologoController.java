package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.model.Odontologo;
import com.dh.ClinicMVC.service.IOdontologoService;
import com.dh.ClinicMVC.service.implementation.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        Odontologo nuevoOdontologo = odontologoService.guardar(odontologo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoOdontologo);
    }
    @GetMapping
    public  ResponseEntity<List<Odontologo>> listarTodos() {
        List<Odontologo> listaBuscados = odontologoService.listarTodos();
        if (!listaBuscados.isEmpty()) {
            return ResponseEntity.ok(listaBuscados);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id) {
        Odontologo odontologo = odontologoService.buscarPorId(id);
       return odontologo != null ? ResponseEntity.ok(odontologo) : ResponseEntity.notFound().build();
    }
    @DeleteMapping
    public ResponseEntity<String> eliminar(@RequestParam("id") Integer id) {
        ResponseEntity<String> response;
        Odontologo odontologoBuscado = odontologoService.buscarPorId(id);
        if (odontologoBuscado != null) {
            odontologoService.eliminar(id);
            response = ResponseEntity.ok("Se elimino el odontologo con id " + id);
        }
        else {
            response = ResponseEntity.ok().body("No se encontro el odontologo");
        }
        return response;
    }
    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Odontologo odontologo) {
        ResponseEntity<String> response;
        Odontologo odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBuscado != null) {
            odontologoService.actualizar(odontologo);
            response = ResponseEntity.ok("Se actualizó el odontologo con id " + odontologo.getId());
        } else {
            response = ResponseEntity.ok().body("No se puede actualizar el odontologo");
        }
        return response;

    }
}

