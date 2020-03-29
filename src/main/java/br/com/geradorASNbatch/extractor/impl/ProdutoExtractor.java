package br.com.geradorASNbatch.extractor.impl;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import br.com.geradorASNbatch.extractor.BasicExtractor;
import br.com.geradorASNbatch.model.Produto;

public class ProdutoExtractor implements BasicExtractor<Produto> {

	private static final Logger log = LoggerFactory.getLogger(EmpresaExtractor.class);
	
	public ProdutoExtractor() {}

	@SuppressWarnings("resource")
	public List<Produto> extrairDadosCSV() throws IOException {

		log.info("Iniciando a extração dos dados do arquivo produtos.csv");
		List<Produto> produtos = new ArrayList<Produto>();

		@SuppressWarnings("deprecation")
		CSVReader reader = new CSVReader(new FileReader("src/main/resources/produtos.csv"), ';' , '"' , 1);
		List<String[]> produtoStringList = reader.readAll();
		
		for (String[] produto : produtoStringList) {
			produtos.add(new Produto().setPartNumber(produto[0]).setCai(produto[1]).setCad(produto[2])
					.setModelo(produto[3]).setTipoProduto(produto[4]).setUnidadeMedida(produto[5])
					.setVolume(Double.parseDouble(produto[6])).setAltura(Double.parseDouble(produto[7]))
					.setLargura(Double.parseDouble(produto[8])).setComprimento(Double.parseDouble(produto[9])));			
		}
		
		log.info("Finalizando a extração dos dados do arquivo produtos.csv");
		return produtos;

	}

}
