package com.dh.ClinicMVC.service.implementation;


import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.repository.IOdontologoRepository;
import com.dh.ClinicMVC.service.IOdontologoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private static IOdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
    @Override
    public Odontologo guardar(Odontologo odontologo) throws Exception {
        if (odontologo.getNombre() == null || odontologo.getApellido() == null || odontologo.getMatricula()== null
                || odontologo.getNombre().trim() == "" || odontologo.getApellido().trim() == "" || odontologo.getMatricula().trim() == "") {
            throw new Exception("No puedes poner campos vacios");
        }
        return odontologoRepository.save(odontologo);
    }
    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Optional<Odontologo> buscarPorId(Long id) {
      return odontologoRepository.findById(id);
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public Optional<List<Odontologo>> findByNombre(String nombre) {
       return odontologoRepository.findByNombre(nombre);
    }

    @Override
    public Optional<List<Odontologo>> findByApellido(String apellido) {
        return odontologoRepository.findByApellido(apellido);
    }


    @Override
    public Optional<List<Odontologo>> findByMatricula(String matricula) {
        return odontologoRepository.findByMatricula(matricula);
    }


}
