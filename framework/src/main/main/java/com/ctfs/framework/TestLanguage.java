package com.ctfs.framework;

import com.ctfs.framework.Constants.Language;

public class TestLanguage {
	static private ThreadLocal<Language> language = new ThreadLocal<Language>();
	
	public static Language getLanguage() {
		System.out.println("GETTING LANGUAGE=" + language.get());
		return language.get();
	}
	
	public static void setLanguage(Language lang) {
		System.out.println("SETTING LANGUAGE=" + lang);
		language.set(lang);
	}
	
	public static void setLanguage(String lang) {
		System.out.println("SETTING LANGUAGE1=" + lang);
		lang = lang.toUpperCase();
		if (lang.startsWith("E")) {
			language.set(Language.English);
		} else if (lang.startsWith("F")) {
			language.set(Language.French);
		}else if (lang.startsWith("O")) {
			language.set(Language.Onboarding);
		} else if (lang.equals("")) {
			language.set(null);
		} else {
			throw new RuntimeException("Invalid language=" + lang);
		}
	}

}
