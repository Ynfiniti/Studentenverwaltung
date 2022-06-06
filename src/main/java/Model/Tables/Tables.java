package Model.Tables;

import java.util.HashMap;

public class Tables {
	enum Table{Schueler, Kurs, Unternehmen}
	private final HashMap<Table, Integer> tables;
	private volatile Tables tablesSingelton;

	public static Tables getInstance(){
		if()
	}

	private Tables() {
		this.tables = new HashMap<>();
	}


}
