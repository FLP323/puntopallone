package it.ur3.siw.controller.admin;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InstantEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) {
		if (text == null || text.trim().isEmpty()) {
			setValue(null);
			return;
		}
		DateTimeFormatter[] formatters = {
				DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
				DateTimeFormatter.ISO_LOCAL_DATE_TIME };
		for (DateTimeFormatter fmt : formatters) {
			try {
				LocalDateTime ldt = LocalDateTime.parse(text.trim(), fmt);
				setValue(ldt.atZone(ZoneId.of("Europe/Rome")).toInstant());
				return;
			} catch (Exception ignored) {
			}
		}
		throw new IllegalArgumentException("Formato data/ora non valido");
	}
}