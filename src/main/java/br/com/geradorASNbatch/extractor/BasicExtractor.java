package br.com.geradorASNbatch.extractor;

import java.io.IOException;
import java.util.List;

public interface BasicExtractor<T> {
	public List<T> extrairDadosCSV() throws IOException;
}
