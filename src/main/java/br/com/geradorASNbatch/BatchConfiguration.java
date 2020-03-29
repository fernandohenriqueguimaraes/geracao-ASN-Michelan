package br.com.geradorASNbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.geradorASNbatch.batch.task.GerarTabelaEmpresaTask;
import br.com.geradorASNbatch.batch.task.GerarTabelaProdutoTask;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Bean
	public Step gerarTabelaProduto() {
		return steps.get("gerarTabelaProduto").tasklet(new GerarTabelaProdutoTask()).build();
	}

	@Bean
	public Step gerarTabelaEmpresa() {
		return steps.get("gerarTabelaEmpresa").tasklet(new GerarTabelaEmpresaTask()).build();
	}

	@Bean
	public Job geracaoASNJob() {
		return jobs.get("geracaoASNJob").start(gerarTabelaProduto())
				.next(gerarTabelaEmpresa()).build();
	}

}
