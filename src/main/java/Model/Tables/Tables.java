package Model.Tables;

import java.util.HashMap;

public class Tables {
	enum Table{Schueler, Kurs, Unternehmen}
	private final HashMap<Table, Integer> tables;
	private static volatile Tables tablesSingelton = null;

	public static Tables getInstance(){
		if(tablesSingelton == null){
			synchronized (tablesSingelton){
				if(tablesSingelton == null){
					tablesSingelton = new Tables();
				}
			}
		}
		return tablesSingelton;
	}

	private Tables() {
		this.tables = new HashMap<>();
	}


}
