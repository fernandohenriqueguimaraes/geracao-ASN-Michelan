package br.com.geradorASNbatch.batch.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import br.com.geradorASNbatch.database.connection.ConexaoBDApacheDerby;

public class ConsultarNFNimbi implements Tasklet {
	
	private static final Logger log = LoggerFactory.getLogger(ConsultarNFNimbi.class);

	private static int HTTP_COD_SUCESSO = 200;
	
	private static final String CLIENTAPI_ID 	= "ClientAPI_ID";
	private static final String CLIENTAPI_KEY 	= "ClientAPI_KEY";
	
	private static final String ID 				= "b0d4bbcf-ff2f-474c-81f3-40eb79026151";
	private static final String KEY 			= "ea3fdbe7-be1b-4437-b513-1fd13d008ee1";
	

	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
	try {
        URL url = new URL("https://api001.nimbi.com.br/API/rest/NFe/ResponseProcess/v1?StartDate=2020-01-26T00:00:00Z&EndDate=2020-02-28T23:59:59Z");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET"); 
        connection.setRequestProperty("Content-Type", "text/json");
        connection.setRequestProperty(CLIENTAPI_ID, ID);
        connection.setRequestProperty(CLIENTAPI_KEY, KEY);
        connection.setDoOutput(true);
        
        HashMap<String, String> params = new HashMap<String,String>();
        params.put("StartDate", "2020-01-26T00:00:00Z");
        params.put("EndDate", "2020-02-28T23:59:59Z");
        
        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(getPostDataString(params));
        
        writer.flush();
        writer.close();
        os.close(); 

        if (connection.getResponseCode() != HTTP_COD_SUCESSO) {
            throw new RuntimeException("HTTP error code : "+ connection.getResponseCode());
        }

        log.info(connection.getResponseMessage());
        
        connection.disconnect();

    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    
		return RepeatStatus.FINISHED;
	}
	
	private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

}
