package de.shop.bestellverwaltung.domain;


/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
public enum TransportType {
	STRASSE("ST"),
	SCHIENE("SCH"),
	LUFT("L"),
	WASSER("W");
	
	private String dbString;
	
	private TransportType(String dbString) {
		this.dbString = dbString;
	}
	
	public String getDbString() {
		return dbString;
	}
	
	public static TransportType build(String dbString) {
		switch (dbString) {
			case "ST":
				return STRASSE;
			case "SCH":
				return SCHIENE;
			case "L":
				return LUFT;
			case "W":
				return WASSER;
			default:
				throw new RuntimeException(dbString + " ist kein gueltiger Wert fuer TransportType");
		}
	}
}
