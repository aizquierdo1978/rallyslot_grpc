package com.alberto.rallyslot.tareas;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberto.rallyslot.common.Constantes;
import com.alberto.rallyslot.service.tramo.TramoService;

@Component
public class TareaInicializarTramos implements Tasklet {

	private static final Log LOG = LogFactory.getLog(TareaInicializarTramos.class);

	@Autowired
	private TramoService tramoService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		//
		LOG.debug(
				"[TareaInicializarTramos] Se van buscar todos aquellos tramos sin tiempos para poner el de por defecto");
		// Se van buscar todos aquellos tramos sin tiempos para poner el de por defecto
		tramoService.initializeTramos(BigDecimal.valueOf(Constantes.TIEMPO_TRAMO), Constantes.BATCH_USER);
		LOG.debug("[TareaInicializarTramos] Se han actualizado los tramos sin tiempo");
		return RepeatStatus.FINISHED;
	}


}
