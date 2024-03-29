package com.dh.ClinicMVC.service.implementation;
import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.repository.IOdontologoRepository;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(OdontologoServiceTest.class);
    @Mock
    private IOdontologoRepository odontologoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void guardar() {

    }

    @Test
    public void testListarTodos() {
        Odontologo odontologo1 = new Odontologo();
        odontologo1.setNombre("Jorgito");
        odontologo1.setApellido("Pérez");
        odontologo1.setMatricula("12345");


        Odontologo odontologo2 = new Odontologo();
        odontologo2.setNombre("Vanina");
        odontologo2.setApellido("Godoy");
        odontologo2.setMatricula("33344");

       OdontologoService odontologoService = new OdontologoService(odontologoRepository);
       try {

        odontologoRepository.save(odontologo1);
       } catch (Exception e) {
           e.printStackTrace();
       }
        odontologoRepository.save(odontologo2);

        // Busca todos los odontólogos por nombre
        Optional<List<Odontologo>> odontologos = odontologoRepository.findByNombre("Jorgito");

        assertTrue(odontologos.isPresent());
        assertEquals(1, odontologos.get().size());
        assertEquals("Jorgito", odontologos.get().get(0).getNombre());
    }

    @Test
    void buscarPorId() {
    }

    @Test
    void actualizar() {
    }

    @Test
    void eliminar() {
    }

    @Test
    void findByNombre() {
    }

    @Test
    void findByApellido() {
    }

    @Test
    void findByMatricula() {
    }

    @Test
    void testGuardar() {
    }


    @Test
    void testBuscarPorId() {
    }

    @Test
    void testActualizar() {
    }

    @Test
    void testEliminar() {
    }

    @Test
    void testFindByNombre() {
    }

    @Test
    void testFindByApellido() {
    }

    @Test
    void testFindByMatricula() {
    }
}