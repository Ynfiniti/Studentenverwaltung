package Model.Tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public record Schueler(IntegerProperty sId, IntegerProperty uId, IntegerProperty kId,
                       StringProperty vorname, StringProperty nachname,
                       char geschlecht, IntegerProperty vorkenntnisse) {

	public IntegerProperty sIdProperty(){
		return sId;
	}

	public int getSId() {
		return sId.get();
	}

	public int getUId() {
		return uId.get();
	}

	public IntegerProperty uIdProperty() {
		return uId;
	}

	public int getKId() {
		return kId.get();
	}

	public IntegerProperty kIdProperty() {
		return kId;
	}

	public String getVorname() {
		return vorname.get();
	}

	public StringProperty vornameProperty() {
		return vorname;
	}

	public String getNachname() {
		return nachname.get();
	}

	public StringProperty nachnameProperty() {
		return nachname;
	}

	public StringProperty geschlechtProperty(){
		SimpleStringProperty retStr = new SimpleStringProperty();
		if(geschlecht == 'm')
			retStr.set("Männlich");
		else if(geschlecht == 'w')
			retStr.set("Weiblich");
		else
			retStr.set("Divers");
		return retStr;
	}

	public char getGeschlecht() {
		return geschlecht;
	}

	public int getVorkenntnisse() {
		return vorkenntnisse.get();
	}

	public IntegerProperty vorkenntnisseProperty() {
		return vorkenntnisse;
	}

	public void setSId(int sId) {
		this.sId.set(sId);
	}

	public void setUId(int uId) {
		this.uId.set(uId);
	}

	public void setKId(int kId) {
		this.kId.set(kId);
	}

	public void setVorname(String vorname) {
		this.vorname.set(vorname);
	}

	public void setNachname(String nachname) {
		this.nachname.set(nachname);
	}

	public void setVorkenntnisse(int vorkenntnisse) {
		this.vorkenntnisse.set(vorkenntnisse);
	}
}
