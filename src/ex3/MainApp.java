package ex3;

import connectionDB.ConnectionDB;

public class MainApp {

	public static void main(String[] args) {
		ConnectionDB conn = new ConnectionDB();
		conn.createDB("ex3");
		conn.createTable("ex3", "Almacenes", "(codigo int PRIMARY KEY, lugar nvarchar(100),"
				+ "capacidad int)");
		conn.createTable("ex3", "Cajas", "(numReferencia char(5) PRIMARY KEY, contenido nvarchar(100),"
				+ "valor int, almacen int, constraint FK_Almacen FOREIGN KEY (almacen) REFERENCES Almacenes(codigo))");
		conn.insertData("ex3", "Almacenes", "VALUES(1,'Valencia',3),"
				+ "(2,'Barcelona',4),"
				+ "(3,'Bilbao',7),"
				+ "(4,'Los Angeles',2),"
				+ "(5,'San Francisco',8)");
		conn.insertData("ex3", "Cajas", "VALUES('0MN7','Rocks',180,3),"
				+ "('4H8P','Rocks',250,1),"
				+ "('4RT3','Scissors',190,4),"
				+ "('7G3H','Rocks',200,1),"
				+ "('8JN6','Papers',75,1),"
				+ "('8Y6U','Papers',50,3),"
				+ "('9J6F','Papers',175,2),"
				+ "('LL08','Rocks',140,4),"
				+ "('P0H6','Scissors',125,1),"
				+ "('P2T6','Scissors',150,2),"
				+ "('TU55','Papers',90,5)");
		conn.getValues("ex3", "Almacenes");
		conn.getValues("ex3", "Cajas");
		conn.closeConnection();
	}

}
