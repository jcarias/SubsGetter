package com.quina.subsgetter.model;

public class Language {
	private String subLanguageID;
	private String languageName;
	private String iso639;

	public String getSubLanguageID() {
		return subLanguageID;
	}

	public void setSubLanguageID(String subLanguageID) {
		this.subLanguageID = subLanguageID;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getIso639() {
		return iso639;
	}

	public void setIso639(String iso639) {
		this.iso639 = iso639;
	}

	public Language() {
		super();
	}

	public Language(String subLanguageID, String languageName, String iso639) {
		super();
		this.subLanguageID = subLanguageID;
		this.languageName = languageName;
		this.iso639 = iso639;
	}

}
