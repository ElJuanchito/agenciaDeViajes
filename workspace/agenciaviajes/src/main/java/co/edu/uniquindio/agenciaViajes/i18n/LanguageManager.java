package co.edu.uniquindio.agenciaViajes.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;

public class LanguageManager {
	private static final String ROUTE = "/co/edu/uniquindio/agenciaviajes/properties/fxmlprop";
	private static LanguageManager instance;
	private SimpleObjectProperty<ResourceBundle> bundleProperty;

	public static LanguageManager getInstance() {
		if (instance == null)
			instance = new LanguageManager();
		return instance;
	}

	private LanguageManager() {
		bundleProperty = new SimpleObjectProperty<>(ResourceBundle.getBundle(ROUTE));
	}

	public void addListener(Traducible traducible) {
		applyTranslation(traducible);
		bundleProperty.addListener((observable, oldValue, newValue) -> traducible.updateLanguage(newValue));
	}

	public void applyTranslation(Traducible traducible) {
		traducible.updateLanguage(getBundle());
	}

	private ResourceBundle getBundle() {
		return bundleProperty.getValue();
	}

	public void setLanguage(Locale locale) {
		if (!locale.equals(getBundle().getLocale()))
			bundleProperty.setValue(ResourceBundle.getBundle(ROUTE, locale));
	}

	public void setLanguage(String localeString) {
		setLanguage(new Locale.Builder().setLanguage(localeString).build());
	}

	public String getString(String key) {
		return getBundle().getString(key);
	}
}