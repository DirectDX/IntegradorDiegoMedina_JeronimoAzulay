package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.dto.request.TurnoRequestDTO;
import com.dh.ClinicMVC.dto.response.TurnoResponseDTO;
import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.entity.Turno;

import com.dh.ClinicMVC.repository.IOdontologoRepository;
import com.dh.ClinicMVC.repository.IPacienteRepository;
import com.dh.ClinicMVC.repository.ITurnosRepository;
import com.dh.ClinicMVC.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private ITurnosRepository turnoRepository;
    private IPacienteRepository pacienteRepository;
    private IOdontologoRepository odontologoRepository;

    @Autowired
    public TurnoService(ITurnosRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }


    @Override
    public TurnoResponseDTO guardar(TurnoRequestDTO turnoRequestDTO) throws Exception {

        //convertir el String del turnoRequestDTO a LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //creamos el LocalDate que vamos a tener que persistir en la BD
        LocalDate date = LocalDate.parse(turnoRequestDTO.getFecha(), formatter);

        // chequeamos que el turno no tenga el mismo odontologo en la misma fecha y hora que un turno ya guardado
        Optional<List<Turno>> turnosChequeoOdontologo = findByOdontologoId(turnoRequestDTO.getOdontologo_id());
        if (turnosChequeoOdontologo.isPresent() && !turnosChequeoOdontologo.get().isEmpty()) {
            List<Turno> turnos = turnosChequeoOdontologo.get();
            for (Turno turno:turnos) {
                if (turno.getFecha().equals(date)) {
                    throw new Exception("Este odontologo ya tiene un turno asignado en esta fecha y hora");
                }
            }
        }

//        // chequeamos que el turno no tenga el mismo odontologo en la misma fecha y hora que un turno ya guardado
//        Optional<List<Turno>> turnosChequeoPaciente = findByPacienteId(turnoRequestDTO.getPaciente_id());
//        if (turnosChequeoPaciente.isPresent() && !turnosChequeoPaciente.get().isEmpty()) {
//            List<Turno> turnos = turnosChequeoPaciente.get();
//            for (Turno turno:turnos) {
//                if (turno.getFecha().equals(date)) {
//                    throw new Exception("No puedes poner campos vacios");
//                }
//            }
//        }

        //mapear el dto que recibimos a una entidad
        //instanciar turnoEntity -> para persitirlo en la BD
        Turno turnoEntity = new Turno(); //Turno(null, null, null, null)

        //instanciamos Paciente
        Paciente paciente = new Paciente();
        paciente.setId(turnoRequestDTO.getPaciente_id());

        //instanciamos Odontologo
        Odontologo odontologo = new Odontologo();
        odontologo.setId(turnoRequestDTO.getOdontologo_id());

        //seteamos Paciente y Odontologo a la entidad Turno
        turnoEntity.setOdontologo(odontologo);
        turnoEntity.setPaciente(paciente);

        //seteamos al turno la fecha
        turnoEntity.setFecha(date);

        //persistir el turno en la BD
        turnoRepository.save(turnoEntity);
        //acá ya tenemos la entidad con id

        //ESTE ES EL CAMINO DE VUELTA HACIA EL CONTROLADOR
        //PORQUE YA SE PERSISTIÓ LA ENTIDAD
        //mapear la entidad persistida en un dto
        TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO(); //TurnoDto(null, null, null, null)
        turnoResponseDTO.setId(turnoEntity.getId());
        turnoResponseDTO.setOdontologo_id(turnoEntity.getOdontologo().getId());
        turnoResponseDTO.setPaciente_id(turnoEntity.getPaciente().getId());
        turnoResponseDTO.setFecha(turnoEntity.getFecha().toString());

        return turnoResponseDTO;
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }

    @Override
    public Turno buscarPorId(Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if (turnoOptional.isPresent())
            return turnoOptional.get();
        else {
            return null;
        }
    }

    @Override
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public void actualizar(Turno turno) {
    turnoRepository.save(turno);
    }

    @Override
    public Optional<List<Turno>> findByOdontologoId(Long id) {
        return turnoRepository.findByOdontologoId(id);
    }

    @Override
    public Optional<List<Turno>> findByPacienteId(Long id) {
        return findByPacienteId(id);
    }
}
