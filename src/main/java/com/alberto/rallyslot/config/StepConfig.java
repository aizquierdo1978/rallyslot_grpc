package com.alberto.rallyslot.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alberto.rallyslot.tareas.TareaInicializarTramos;
import com.alberto.rallyslot.tareas.TareaInscritoSinTramos;

@Configuration
public class StepConfig {

	/**
	 * Inscritos sin tramos step.
	 *
	 * @param stepBuilderFactory     the step builder factory
	 * @param tareaInscritoSinTramos the tarea inscrito sin tramos
	 * @return the step
	 */
	@Bean
	public Step inscritosSinTramosStep(@Autowired StepBuilderFactory stepBuilderFactory,
			@Autowired TareaInscritoSinTramos tareaInscritoSinTramos) {
		return stepBuilderFactory.get("inscritosSinTramosStep").tasklet(tareaInscritoSinTramos).build();
	}

	/**
	 * Inicializar tramos step.
	 *
	 * @param stepBuilderFactory     the step builder factory
	 * @param tareaInicializarTramos the tarea inicializar tramos
	 * @return the step
	 */
	@Bean
	public Step inicializarTramosStep(@Autowired StepBuilderFactory stepBuilderFactory,
			@Autowired TareaInicializarTramos tareaInicializarTramos) {
		return stepBuilderFactory.get("inicializarTramosStep").tasklet(tareaInicializarTramos).build();
	}

}