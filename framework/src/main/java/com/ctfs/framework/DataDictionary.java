package com.ctfs.framework;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ctfs.common.utils.ApplicationProperties;
import com.ctfs.framework.Constants.Language;


public class DataDictionary {
	 
	private static Logger logger = LoggerFactory.getLogger(DataDictionary.class);
	
	public static String getDataDictionaryText(String errorKeyOrMessage) {
		logger.info(String.format("Retrieving data dictionary entry for key: [%s]", errorKeyOrMessage));
		String parts[]  = errorKeyOrMessage.split("dd:");
		String language = "E";

		return getMessageFromDataDictionary(language, parts[1]);
	}
	
	public static String getMessageFromDataDictionary(Language lang, String key) {
		if (lang == Language.English) {
			return getMessageFromDataDictionary("E", key);
		} else if (lang == Language.French) {
			return getMessageFromDataDictionary("F", key);
		}
		return null;
	}
	
	public static String getMessageFromDataDictionary(String lang, String key) {
		String _option = "DASH";
        if(System.getProperty("dataDictionary") != null){
            _option = System.getProperty("dataDictionary");
        }

		try {
			return getMessageFromDataDictionary(lang, key, _option);
		} catch(Exception e) {
			if(!_option.equalsIgnoreCase("dash")) {
				logger.info("Failed to locate message in non-DASH data dictionary - Checking DASH dictionary..");
				return getMessageFromDataDictionary(lang, key, "dash");
			}
		}
		return "";
	}
	
	public static String getMessageFromDataDictionaryDontStripHTML(String lang, String key) {
		String _option = "DASH";
        if(System.getProperty("dataDictionary") != null){
            _option = System.getProperty("dataDictionary");
        }

		try {
			return getMessageFromDataDictionaryDontStripHTML(lang, key, _option);
		} catch(Exception e) {
			if(!_option.equalsIgnoreCase("dash")) {
				logger.info("Failed to locate message in non-DASH data dictionary - Checking DASH dictionary..");
				return getMessageFromDataDictionary(lang, key, "dash");
			}
		}
		return "";
	}
	
	public static String getMessageFromDataDictionary(String lang, String key, String _dd) {
		logger.info(String.format("Retrieving message from data dictionary for key: [%s] in language: [%s]", key, lang));
		try {
			String _path   = getDDPath(lang, _dd);
			String _value  = Jsoup.parse(searchDD(key, getResourcePath(_path), _dd)).text();
			logger.info(String.format("Key value: [%s]", key));
			logger.info(String.format("Message found: [%s]", _value));
			return _value.trim();
		} catch (Exception e) {
			logger.error("An error occurred while attempting to get the message from the data dictionary", e);
		}
		return "";
	}
	
	public static String getMessageFromDataDictionaryDontStripHTML(String lang, String key, String _dd) {
		logger.info(String.format("Retrieving message from data dictionary for key: [%s] in language: [%s]", key, lang));
		try {
			String _path   = getDDPath(lang, _dd);
			String _value  = searchDD(key, getResourcePath(_path),_dd);
			logger.info(String.format("Key value: [%s]", key));
			logger.info(String.format("Message found: [%s]", _value));
			return _value.trim();
		} catch (Exception e) {
			logger.error("An error occurred while attempting to get the message from the data dictionary", e);
		}
		return "";
	}
	
	private static String getDDPath(String _lang, String _platform){
		if(_platform.equalsIgnoreCase("DSA")) {
            _lang = _lang.toUpperCase();
            return _lang.startsWith("F") ? "dsaMessages/fr.xml" : "dsaMessages/en.xml";
        }else if(_platform.equalsIgnoreCase("Dash")) {
		_lang = _lang.toUpperCase();
		return _lang.startsWith("O")?  "dashMessages/onboarding_en.xml" : _lang.startsWith("F") ? "dashMessages/fr.xml" : "dashMessages/en.xml";
		}else {
			_lang = _lang.toUpperCase();
			return  _lang.startsWith("F") ? "wiciMessages/fr.json" : "wiciMessages/en.json";
		}

	}
	

	private static String searchDD(String key, String xmlFile, String _plateform) throws Exception {	
		if (xmlFile.contains(".json")) {
    		JSONParser jsonparser = new JSONParser();
    		FileReader reader = new FileReader(xmlFile);
    		Object obj = jsonparser.parse(reader);
    		JSONObject simpleJsonObj = (JSONObject) obj;
    		String ddValue=(String) simpleJsonObj.get(key);
    		System.out.println("DD value "+ddValue);
    		String encodedString = new String(ddValue.getBytes( "cp1252" ), "UTF-8");
    		return encodedString;
    		
		}
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document        doc      = dBuilder.parse(new File(xmlFile));

        // Normalize the document
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName(key);
        Node     nNode = nList.item(0);
        if (nNode != null) {
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                return ((Element) nNode).getAttribute("sling:message");
            }
        }
        throw new Exception(String.format("Key: [%s] could not be found in the given file: [%s]", key, xmlFile));
        
	}
	
	public static String getResourcePath(String file){
		return new File(Thread.currentThread().getContextClassLoader().getResource(file).getFile()).getAbsolutePath();
	}
	
	public static String getMessageFromJsonDataDictionary(String lang, String key) {
		String _option = "CTFS";
        if(System.getProperty("dataDictionary") != null){
            _option = System.getProperty("dataDictionary");
        }

		try {
			return getMessageFromDataDictionary(lang, key, _option);
		} catch(Exception e) {
			if(!_option.equalsIgnoreCase("ctfs")) {
				logger.info("Failed to locate message in non-CTFS data dictionary - Checking CTFS dictionary..");
				return getMessageFromDataDictionary(lang, key, "ctfs");
			}
		}
		return "";
	}
	public static String getMessageFromJsonDataDictionaryDontStripHTML(String lang, String key) {
		String _option = "CTFS";
        if(System.getProperty("dataDictionary") != null){
            _option = System.getProperty("dataDictionary");
        }

		try {
			return getMessageFromDataDictionaryDontStripHTML(lang, key, _option);
		} catch(Exception e) {
			if(!_option.equalsIgnoreCase("ctfs")) {
				logger.info("Failed to locate message in non-CTFS data dictionary - Checking CTFS dictionary..");
				return getMessageFromDataDictionary(lang, key, "ctfs");
			}
		}
		return "";
	}
}
