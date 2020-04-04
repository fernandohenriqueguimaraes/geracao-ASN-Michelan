package br.com.geradorASNbatch.extractor.impl;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;

import com.opencsv.CSVReader;

import br.com.geradorASNbatch.extractor.BasicExtractor;
import br.com.geradorASNbatch.model.Produto;

public class ProdutoExtractor implements BasicExtractor<Produto> {

	private static final Logger log = LoggerFactory.getLogger(ProdutoExtractor.class);
	
	public ProdutoExtractor() {}

	@SuppressWarnings("resource")
	public List<Produto> extrairDadosCSV() throws IOException {

		List<Produto> produtos = new ArrayList<Produto>();

		@SuppressWarnings("deprecation")
		CSVReader reader = new CSVReader(new FileReader("src/main/resources/produtos.csv"), ';' , '"' , 1);
		List<String[]> produtoStringList = reader.readAll();
		
		for (String[] produto : produtoStringList) {
			produtos.add(new Produto().setPartNumber(produto[0]).setCai(produto[1]).setCad(produto[2])
					.setModelo(produto[3]).setTipoProduto(produto[4]).setUnidadeMedida(produto[5])
					.setVolume(new BigDecimal(produto[6])).setAltura(new BigDecimal(produto[7]))
					.setLargura(new BigDecimal(produto[8])).setComprimento(new BigDecimal(produto[9])));
			
			log.info("Extraindo o PRODUTO: " + Arrays.toString(produto));
		}
		
		return produtos;

	}

}
