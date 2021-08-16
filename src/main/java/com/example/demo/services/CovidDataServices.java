package com.example.demo.services;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import models.Stats;

@Service
public class CovidDataServices  {

	
	private static String URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<Stats> allrecord;
	
	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void fetchData() throws IOException, InterruptedException {
		List<Stats> newstats=new ArrayList<>();
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest request=HttpRequest.newBuilder(URI.create(URL)).build();
		HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
		//System.out.println(httpResponse.body());
		
		
		
		StringReader rd=new StringReader(httpResponse.body());
		
		
		@SuppressWarnings("deprecation")
		CSVParser csvParser = new CSVParser(rd, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase());
		List<CSVRecord> records = csvParser.getRecords();
				
		for (CSVRecord record : records) {
			Stats locationStat=new Stats();
			locationStat.setCountry(record.get("Country/Region"));
			locationStat.setState(record.get("Province/State"));
		    locationStat.setLatestCases(Integer.parseInt(record.get(record.size()-1)));
		    locationStat.setChange(Integer.parseInt(record.get(record.size()-1))-Integer.parseInt(record.get(record.size()-2)));
		    //System.out.println(locationStat);
		    newstats.add(locationStat);
		}
		this.allrecord=newstats;
	}

	public List<Stats> getAllrecord() {
		return allrecord;
	}

	public void setAllrecord(List<Stats> allrecord) {
		this.allrecord = allrecord;
	}
	
}
