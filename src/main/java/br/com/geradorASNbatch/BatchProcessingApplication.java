package br.com.geradorASNbatch;

import java.sql.SQLException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.geradorASNbatch.database.connection.ConexaoBDApacheDerby;
import br.com.geradorASNbatch.database.table.impl.EmpresaTable;
import br.com.geradorASNbatch.database.table.impl.ProdutoTable;

@SpringBootApplication
public class BatchProcessingApplication implements CommandLineRunner{

    @Autowired
    JobLauncher jobLauncher;
     
    @Autowired
    Job job;
    
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BatchProcessingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			ConexaoBDApacheDerby.connectionToDerby();
			EmpresaTable.dropTable();
			ProdutoTable.dropTable();
			EmpresaTable.createTable();
			ProdutoTable.createTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JobParameters params = new JobParametersBuilder()
                 .addString("JobID", String.valueOf(System.currentTimeMillis()))
                 .toJobParameters();
		jobLauncher.run(job, params);
		
	}
}
