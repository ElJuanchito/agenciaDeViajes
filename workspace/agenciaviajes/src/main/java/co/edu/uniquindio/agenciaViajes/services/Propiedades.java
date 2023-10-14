package co.edu.uniquindio.agenciaViajes.services;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;

public class Propiedades {
	private static final String ROUTE = "/co/edu/uniquindio/agenciaviajes/properties/fxmlprop";
	private static Propiedades instance;
	private SimpleObjectProperty<ResourceBundle> bundleProperty;

	public static Propiedades getInstance() {
		if (instance == null)
			instance = new Propiedades();
		return instance;
	}

	private Propiedades() {
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
}