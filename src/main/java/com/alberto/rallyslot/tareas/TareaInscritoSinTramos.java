package com.alberto.rallyslot.tareas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberto.rallyslot.service.inscrito.InscritoService;

@Component
public class TareaInscritoSinTramos implements Tasklet {

	private static final Log LOG = LogFactory.getLog(TareaInscritoSinTramos.class);

	@Autowired
	private InscritoService inscritoService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		LOG.debug("[TareaInscritoSinTramos] Se van a buscar los inscritos sin tramos y se van a crear");
		// Se van a buscar aquellos inscritos sin tramos
		inscritoService.comprobarInscritosSinTramos();
		LOG.debug("[TareaInscritoSinTramos] Se han actualizado los pilotos sin tramo");
		return RepeatStatus.FINISHED;
	}

}
