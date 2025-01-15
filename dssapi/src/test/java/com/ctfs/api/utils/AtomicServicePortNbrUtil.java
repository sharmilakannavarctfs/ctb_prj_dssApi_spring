package com.ctfs.api.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ctfs.common.utils.ApplicationProperties;

@Component
public class AtomicServicePortNbrUtil {

	public static ApplicationProperties propertyLoader = new ApplicationProperties();

//	@Test
//	public void test_eureka() {
//		System.out.println(getPort("TS2SERVICE_V1"));
//	}

	
	public String getPort(String microService) {

		String environment = "DEV";
//		String environment = propertyLoader.getEnvironment();
		String eureka_html = null;
		if(environment.equals("DEV"))
			eureka_html = "d9cbwpdssa01";
		else if (environment.equals("QA"))
			eureka_html = "q9dssa03";
		//to get eureka HTML

		String result;
		result = getEurekaHTML(eureka_html);

		String portNumber = "";

		try{
			// Removing all empty spaces and splitting with </tr> as delimiter
			String[] services = result.substring(result.indexOf("<tbody>") + 8, result.indexOf("</tbody>"))
					.replaceAll("<tr>", "").replaceAll("\\s", "").split("</tr>");

			// patterns to match service and corresponding ports
			Pattern servicePattern = Pattern.compile(".*(<b>)([A-Z]+.*)(</b>).*");
			Pattern portPatternDEV = Pattern.compile(".*("+eureka_html+".*:)(\\d{4,5})(/.*)");
//			Pattern portPatternDEV_localhost = Pattern.compile(".*(localhost:)(\\d{4,5})(/.*)");
			Pattern portPatternQA = Pattern.compile(".*("+eureka_html+".ctal.ctc:)(\\d{4,5})(/.*)");

			Matcher matcher;			

			for (String string : services) {
				String[] subStrings = string.replace("<td>", "").split("</td>");
				//				System.out.println("-------");


				for (String string2 : subStrings) {
					//					System.out.println(string2);

					matcher = servicePattern.matcher(string2);
					if(matcher.matches() && matcher.group(2).equals(microService) ) 
					{ 
						if(environment.equals("d9cbwpdssa01")) {
							String[] devStrings = subStrings[3].split(",");
							for (String string4 : devStrings) {
								if(string4.contains("d9cbwpdssa01"))
									matcher =portPatternDEV.matcher(string4);
//								else if(string4.contains("localhost"))
//									matcher =portPatternDEV_localhost.matcher(string4);

							}

						}

						else 
							matcher =portPatternQA.matcher(subStrings[3]); 

						matcher.matches();
						portNumber = matcher.group(2); 
						break;
					}
				}
			}

		}
		catch(IllegalStateException e) {
			System.out.println("Issue with Pattern matching -- Probable cause HTML code change");
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return portNumber;
	}

	public  String getEurekaHTML(String eureka_html) {
		eureka_html = "http://"+eureka_html+":8761";
		String[] command = { "curl", eureka_html };
		String result = "";

//		System.out.println(eureka_html);
		ProcessBuilder process = new ProcessBuilder(command);
		Process p;
		try {
			p = process.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
			}
			result = builder.toString();

		} catch (IOException e) {
			System.out.print("error");
			e.printStackTrace();
		}
		return result;
	}

}
