package de.shop.kundenverwaltung.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Converter(autoApply = true)
public class EigenschaftenConverter implements AttributeConverter<Eigenschaften, String> {
	@Override
	public String convertToDatabaseColumn(Eigenschaften hobbyType) {
		if (hobbyType == null) {
			return null;
		}
		return hobbyType.getInternal();
	}

	@Override
	public Eigenschaften convertToEntityAttribute(String internal) {
		return Eigenschaften.build(internal);
	}
}
