package com.test.springmysql.mocks;

import com.test.springmysql.config.InicializarDatos;
import com.test.springmysql.entities.Comision;
import com.test.springmysql.entities.Estudiante;
import com.test.springmysql.entities.Materia;
import com.test.springmysql.entities.Profesor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ComisionMock {

    static int tamMaterias = MateriaMock.NOMBRES.length;

    static LocalTime[][] rangosHorarios = {
            {LocalTime.of(9, 0), LocalTime.of(13, 0)},  // Rango 0
            {LocalTime.of(14, 0), LocalTime.of(18, 0)}, // Rango 1
            {LocalTime.of(18, 0), LocalTime.of(22, 0)}  // Rango 2
    };

    static LocalDate fechaInicioMin = LocalDate.of(2025, 4, 2);
    static LocalDate fechaInicioMax = LocalDate.of(2025, 11, 12);




    public static List<Comision> crearComision(
            List<Estudiante> estudiantes,
            List<Profesor> profesores,
            List<Materia> materias
    ) {


        List<Comision> lista = new ArrayList<>();
        int MIN_ALUM_PERMITIDOS = 20;
        int MAX_ALUM_PERMITIDOS = 60;


        // agregar una comision a las primeras 5 materias
        for (int i = 0; i < tamMaterias - 2; i++) {
            Comision c = new Comision();

            int alumPermitidos = generarCantAlumnosPermitidos(MIN_ALUM_PERMITIDOS, MAX_ALUM_PERMITIDOS);
            c.setAlumnos_permitidos(alumPermitidos);

            //SETEAR FECHA
            LocalDate fechaInicio = generarFechaInicio(fechaInicioMin,fechaInicioMax);
            c.setFecha_inicio(fechaInicio);
            c.setFecha_final(fechaInicio.plusMonths(4));


            //SETEAR HORA
            // Elegimos un índice aleatorio entre 0 y 2
            int indice = ThreadLocalRandom.current().nextInt(rangosHorarios.length);

            // 3. Extraemos el inicio y el fin del rango elegido
            LocalTime desde = rangosHorarios[indice][0];
            LocalTime hasta = rangosHorarios[indice][1];


            c.setHora_inicio(desde);
            c.setHora_final(hasta);

            Materia m = materias.get(i);
            c.setMateria(m);

            List<Profesor> profes = new ArrayList<>();
            profes.add(getProfesorAleatoriamente(profesores));
            c.setProfesores(profes);

            c.setEstudiantes(getEstudiantesAleatoriamente(estudiantes, InicializarDatos.CANT_ESTUDIANTES,alumPermitidos));

            lista.add(c);
        }

        //agregar dos comisiones a las ultimas dos materias
        for(int i = tamMaterias - 2; i<tamMaterias;i++ ){
            for(int j = 0 ; j < 2; j++){
                Comision c = new Comision();

                int alumPermitidos = generarCantAlumnosPermitidos(MIN_ALUM_PERMITIDOS, MAX_ALUM_PERMITIDOS);
                c.setAlumnos_permitidos(alumPermitidos);

                //SETEAR FECHA
                LocalDate fechaInicio = generarFechaInicio(fechaInicioMin,fechaInicioMax);
                c.setFecha_inicio(fechaInicio);
                c.setFecha_final(fechaInicio.plusMonths(4));


                //SETEAR HORA
                // Elegimos un índice aleatorio entre 0 y 2
                int indice = ThreadLocalRandom.current().nextInt(rangosHorarios.length);

                // 3. Extraemos el inicio y el fin del rango elegido
                LocalTime desde = rangosHorarios[indice][0];
                LocalTime hasta = rangosHorarios[indice][1];


                c.setHora_inicio(desde);
                c.setHora_final(hasta);

                Materia m = materias.get(i);
                c.setMateria(m);

                List<Profesor> profes = new ArrayList<>();
                profes.add(getProfesorAleatoriamente(profesores));
                c.setProfesores(profes);

                c.setEstudiantes(getEstudiantesAleatoriamente(estudiantes, InicializarDatos.CANT_ESTUDIANTES,alumPermitidos));

                lista.add(c);
            }
        }


        return lista;
    }


    public static int generarCantAlumnosPermitidos(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static LocalDate generarFechaInicio(LocalDate min, LocalDate max) {

        // 2. Convertir a días de época
        long inicioEpoch = min.toEpochDay();
        long finEpoch = max.toEpochDay();

        // 3. Generar un día aleatorio entre ambos (el fin es exclusivo, sumamos 1 para incluirlo)
        long diaAleatorioEpoch = ThreadLocalRandom.current().nextLong(inicioEpoch, finEpoch + 1);

        // 4. Convertir de nuevo a LocalDate
        return LocalDate.ofEpochDay(diaAleatorioEpoch);
    }

    public static Profesor getProfesorAleatoriamente(List<Profesor> profesores){
        Random random = new Random();
        //solo funciona cuando profesores.size() == ProfesorMock.NOMBRES.length
        int indice = random.nextInt(ProfesorMock.NOMBRES.length);
        Profesor p = profesores.get(indice);
        return p;
    }

    public static List<Estudiante> getEstudiantesAleatoriamente(List<Estudiante> estudiantes, int cantEst, int alumPer){
        List<Estudiante> list = new ArrayList<>();

        Random random = new Random();


        int cant = random.nextInt(alumPer);
        while(cant > cantEst){
            cant = random.nextInt(alumPer);
        }
        Collections.shuffle(estudiantes);
        for(int i = 0; i < cant; i++){
            list.add(estudiantes.get(i));
        }

        return list;
    }


}
