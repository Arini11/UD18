package ex9;

import connectionDB.ConnectionDB;

public class MainApp {

	public static void main(String[] args) {
ConnectionDB con = new ConnectionDB();
		
		String nomDB = "LosInvestigadores";
		String nomTabla1 = "facultades";
		String nomTabla2 = "investigadores";
		String nomTabla3 = "equipos";
		String nomTabla4 = "reserva";
		
		con.openConnection(); //obro per poder fer drop de la database
		con.dropElement("DATABASE",nomDB);
		
		// ja inclou verificar si la conexio es null no cal usar openConnection() 
		con.createDB(nomDB); 
		
		con.createTable(nomDB,nomTabla1,"(id int PRIMARY KEY, nombre varchar(100));");
		con.createTable(nomDB,nomTabla2, "(DNI varchar(9) PRIMARY KEY, nomApels varchar(255), facultad int, "
				+ "CONSTRAINT FK_investigFacultad FOREIGN KEY (facultad) REFERENCES facultades(id));");
		con.createTable(nomDB,nomTabla3,"(numSerie char(4) PRIMARY KEY, nombre varchar(100), facultad int, "
				+ "CONSTRAINT FK_equipoFacultad FOREIGN KEY (facultad) REFERENCES facultades(id));");
		con.createTable(nomDB,nomTabla4,"(investigador varchar(9), equipo char(4), comienzo date, fin date, "
				+ "PRIMARY KEY(investigador,equipo), "
				+ "CONSTRAINT FK_reservaInvestig FOREIGN KEY (investigador) REFERENCES investigadores(DNI)"
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ " CONSTRAINT FK_reservaEquipo FOREIGN KEY (equipo) REFERENCES equipos(numSerie)"
				+ "ON UPDATE CASCADE ON DELETE CASCADE);");
		
		con.insertData(nomDB, nomTabla1, "VALUES"
				+ "(1,'ciencias'),"
				+ "(2,'educacion'),"
				+ "(3,'economicas'),"
				+ "(4,'ciencias sociales'),"
				+ "(5,'letras');");
		
		con.insertData(nomDB, nomTabla2, "VALUES"
				+ "('38772956g','marta periago',2),"
				+ "('73846296T','albert periu',1),"
				+ "('84553728Y','maria gomez',5),"
				+ "('74553726T','oscar vidal',3),"
				+ "('57442859H','joana jane',4);");		
		
		con.insertData(nomDB, nomTabla3, "VALUES"
				+ "('E1','equipo 1',3),"
				+ "('E2','equipo 2',2),"
				+ "('E3','equipo 3',4),"
				+ "('E4','equipo 4',5),"
				+ "('E5','equipo 5',1);");
		
		con.insertData(nomDB, nomTabla4, "VALUES"
				+ "('73846296T','E5','2021-02-07',null),"
				+ "('84553728Y','E4','2021-05-10',null),"
				+ "('38772956g','E2','2021-08-18','2022-05-12'),"
				+ "('74553726T','E1','2020-04-15',null),"
				+ "('57442859H','E3','2020-02-10',null);");
		
		con.getValues(nomDB,nomTabla1);
		con.getValues(nomDB,nomTabla2);
		con.getValues(nomDB,nomTabla3);
		con.getValues(nomDB,nomTabla4);
		
		con.deleteRecord(nomTabla4,"investigador","'84553728Y'");
		con.insertData(nomDB, nomTabla4, " VALUES"	 //actualizamos valor de un registro ya existente			
				+ "('57442859H','E3','2020-02-10','2022-05-31');");
		con.getValues(nomDB,nomTabla1);
		con.getValues(nomDB,nomTabla2);
		con.getValues(nomDB,nomTabla3);
		con.getValues(nomDB,nomTabla4);
		
	}

}
