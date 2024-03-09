package com.dh.ClinicMVC.controller;

import com.dh.ClinicMVC.model.Odontologo;
import com.dh.ClinicMVC.service.IOdontologoService;
import com.dh.ClinicMVC.service.implementation.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/odontologo")
public class IndexOdontologoController {

    private IOdontologoService odontologoService;

    public IndexOdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //métodos -> endpoints -> GET/POST/PUT/DELETE
    @GetMapping()
    public String buscarOdontologoPorId(Model model, @RequestParam("id") Integer id) {
        Odontologo odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());
        return "buscarOdontologo";
    }

}
