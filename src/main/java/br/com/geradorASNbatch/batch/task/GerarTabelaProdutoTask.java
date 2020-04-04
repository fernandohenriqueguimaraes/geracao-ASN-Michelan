package br.com.geradorASNbatch.batch.task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import br.com.geradorASNbatch.database.table.impl.ProdutoTable;
import br.com.geradorASNbatch.extractor.impl.ProdutoExtractor;
import br.com.geradorASNbatch.model.Produto;

public class GerarTabelaProdutoTask implements Tasklet {
	
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        List<Produto> produtos = (new ProdutoExtractor()).extrairDadosCSV();
        ProdutoTable table = new ProdutoTable();
        produtos.forEach((produto) -> {
            try {
            	table.insertRow(produto);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
        });
      
        return RepeatStatus.FINISHED;
    }    

}
