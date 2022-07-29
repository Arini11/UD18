package ex7;

import connectionDB.ConnectionDB;

public class MainApp {

	public static void main(String[] args) {
		ConnectionDB con = new ConnectionDB();
		
		String nomDB = "Cientificos";
		String nomTabla1 = "cientificos";
		String nomTabla2 = "asignado_a";
		String nomTabla3 = "proyectos";
		
		con.openConnection(); //obro per poder fer drop de la database
		con.dropElement("DATABASE",nomDB);
		
		// ja inclou verificar si la conexio es null no cal usar openConnection() 
		con.createDB(nomDB); 
		
		con.createTable(nomDB,nomTabla1,"(DNI varchar(9) PRIMARY KEY, nomApels varchar(255));");
		con.createTable(nomDB,nomTabla3, "(id char(4) PRIMARY KEY, nombre varchar(255), horas int);");
		con.createTable(nomDB,nomTabla2,"(cientifico varchar(9), proyecto char(4), "
				+ "PRIMARY KEY(cientifico,proyecto), "
				+ "CONSTRAINT FK_cientificoAsignadoA FOREIGN KEY (cientifico) REFERENCES cientificos(DNI)"
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ " CONSTRAINT FK_ProyectoAsignadoA FOREIGN KEY (proyecto) REFERENCES proyectos(id));");
		
		con.insertData(nomDB, nomTabla1, "VALUES"
				+ "('38772956g','marta periago'),"
				+ "('73846296T','albert periu'),"
				+ "('84553728Y','maria gomez'),"
				+ "('74553726T','oscar vidal'),"
				+ "('57442859H','joana jane');");
		
		con.insertData(nomDB, nomTabla3, "VALUES"
				+ "('P1','estudio floral',200),"
				+ "('P2','estudio amebas',377),"
				+ "('P3','estudio gorilas',433),"
				+ "('P4','estudio covid-19',5000),"
				+ "('P5','estudio matematico',1067);");
		
		con.insertData(nomDB, nomTabla2, "VALUES"
				+ "('38772956g','P1'),"
				+ "('73846296T','P2'),"
				+ "('84553728Y','P5'),"
				+ "('57442859H','P4'),"
				+ "('74553726T','P3');");
		
		con.getValues(nomDB,nomTabla1);
		con.getValues(nomDB,nomTabla2);
		con.getValues(nomDB,nomTabla3);
		
		con.deleteRecord(nomTabla1,"DNI","'57442859H'" );
		con.insertData(nomDB, nomTabla1, "VALUES"	 //actualizamos valor de un registro ya existente			
				+ "('57442859H','Joana Jane Fernandez');");
		con.getValues(nomDB,nomTabla1);
		
		
	}


}
