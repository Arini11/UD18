package ex8;

import connectionDB.ConnectionDB;

public class MainApp {

	public static void main(String[] args) {
		ConnectionDB con = new ConnectionDB();
		
		String nomDB = "GrandesAlmacenes";
		String nomTabla1 = "cajeros";
		String nomTabla2 = "productos";
		String nomTabla3 = "maq_registr";
		String nomTabla4 = "venta";
		
		con.openConnection(); //obro per poder fer drop de la database
		con.dropElement("DATABASE",nomDB);
		
		// ja inclou verificar si la conexio es null no cal usar openConnection() 
		con.createDB(nomDB); 
		
		con.createTable(nomDB,nomTabla1,"(id int PRIMARY KEY, nomApels varchar(255));");
		con.createTable(nomDB,nomTabla2, "(id int PRIMARY KEY, nombre varchar(100), precio int);");
		con.createTable(nomDB,nomTabla3,"(id int PRIMARY KEY, piso int);");
		con.createTable(nomDB,nomTabla4,"(cajero int, producto int, maquina int, "
				+ "PRIMARY KEY(cajero,producto,maquina), "
				+ "CONSTRAINT FK_ventaCajero FOREIGN KEY (cajero) REFERENCES cajeros(id)"
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ " CONSTRAINT FK_VentaProducto FOREIGN KEY (producto) REFERENCES productos(id)"
				+ "ON UPDATE CASCADE ON DELETE CASCADE,"
				+ " CONSTRAINT FK_VentaMaquina FOREIGN KEY (maquina) REFERENCES maq_registr(id)"
				+ "ON UPDATE CASCADE ON DELETE CASCADE);");
		
		
		con.insertData(nomDB, nomTabla1, "VALUES"
				+ "(1,'marta periago'),"
				+ "(2,'albert periu'),"
				+ "(3,'maria gomez'),"
				+ "(4,'oscar vidal'),"
				+ "(5,'joana jane');");
		
		con.insertData(nomDB, nomTabla2, "VALUES"
				+ "(1,'nevera',400),"
				+ "(2,'lampara',50),"
				+ "(3,'mampara',433),"
				+ "(4,'mesa',100),"
				+ "(5,'silla',30);");
		
		con.insertData(nomDB, nomTabla3, "VALUES"
				+ "(1,1),"
				+ "(2,2),"
				+ "(3,5),"
				+ "(4,4),"
				+ "(5,3);");
		
		con.insertData(nomDB, nomTabla4, "VALUES"
				+ "(1,3,1),"
				+ "(2,2,5),"
				+ "(3,1,2),"
				+ "(4,5,3),"
				+ "(5,4,4);");
		
		con.getValues(nomDB,nomTabla1);
		con.getValues(nomDB,nomTabla2);
		con.getValues(nomDB,nomTabla3);
		con.getValues(nomDB,nomTabla4);
		
		con.deleteRecord(nomTabla2,"id","5");
		con.insertData(nomDB, nomTabla2, "VALUES"	 //actualizamos valor de un registro ya existente			
				+ "(4,'mesa cuadrada',100);");
		con.getValues(nomDB,nomTabla2);
		
		
	}

}
