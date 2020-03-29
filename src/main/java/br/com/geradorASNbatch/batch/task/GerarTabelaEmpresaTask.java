package br.com.geradorASNbatch.batch.task;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import br.com.geradorASNbatch.database.table.impl.EmpresaTable;
import br.com.geradorASNbatch.extractor.impl.EmpresaExtractor;
import br.com.geradorASNbatch.model.Empresa;

public class GerarTabelaEmpresaTask implements Tasklet {

	private static final Logger log = LoggerFactory.getLogger(GerarTabelaEmpresaTask.class);

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		List<Empresa> empresas = (new EmpresaExtractor()).extrairDadosCSV();
		EmpresaTable table = new EmpresaTable();
		empresas.forEach((empresa) -> {
			log.info("Extraida do CSV: " + empresa.toString());
			try {
				table.insertRow(empresa);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

		return RepeatStatus.FINISHED;

	}
}
