package br.com.geradorASNbatch.extractor.impl;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;

import br.com.geradorASNbatch.extractor.BasicExtractor;
import br.com.geradorASNbatch.model.Empresa;

public class EmpresaExtractor implements BasicExtractor<Empresa> {

	private static final Logger log = LoggerFactory.getLogger(EmpresaExtractor.class);
	
	public EmpresaExtractor() {}

	@SuppressWarnings("resource")
	public List<Empresa> extrairDadosCSV() throws IOException {

		List<Empresa> empresas = new ArrayList<Empresa>();

		@SuppressWarnings("deprecation")
		CSVReader reader = new CSVReader(new FileReader("src/main/resources/empresas.csv"), ';', '"', 1);
		List<String[]> empresaStringList = reader.readAll();

		for (String[] empresa : empresaStringList) {
			empresas.add(new Empresa().setCnpj(empresa[0]).setBairro(empresa[1]).setCep(empresa[2])
					.setCodigoEmpresa(empresa[3]).setCodigoPais(empresa[4]).setCodigoERP(empresa[5])
					.setComplemento(empresa[6]).geraASNtoBoolean(empresa[7]).setLogradouro(empresa[8])
					.setTipo(empresa[9]).setMunicipio(empresa[10]).setNumero(empresa[11]).setPais(empresa[12])
					.setRazaoSocial(empresa[13]));
			
			log.info("Extraindo a EMPRESA: " + Arrays.toString(empresa));
		}

		return empresas;

	}

}
