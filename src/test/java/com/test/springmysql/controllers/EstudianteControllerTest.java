package com.test.springmysql.controllers;

import com.test.springmysql.services.EstudianteService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//esta anotacion dice que va a ser una clase de prueba que va a testear un controlador.
@WebMvcTest(EstudianteController.class)
public class EstudianteControllerTest {

    @Autowired
    //clase que nos ofrece llamadas a un controlador. Nos permite realizar peticiones aun API REST
    private MockMvc mockMvc;

    @MockitoBean
    private EstudianteService estudianteService;


}
