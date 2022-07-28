package ex1;

import connectionDB.ConnectionDB;

public class MainApp {
	
	private static ConnectionDB conn = null;

	public static void main(String[] args) {
		conn = new ConnectionDB();
		conn.createDB("ex1");
		conn.openConnection();
		conn.createTable("ex1", "Fabricantes", "(codigo int PRIMARY KEY, nombre nvarchar(100))");
		conn.createTable("ex1", "Articulos", " (codigo int PRIMARY KEY, nombre nvarchar(100), precio double, fabricante int,"
				+ "constraint FK_Fabricante FOREIGN KEY (Fabricante) references Fabricantes (codigo))");
		conn.insertData("ex1", "Fabricantes", "VALUES (1,\"fabricante1\")");
		conn.insertData("ex1", "Fabricantes", "VALUES (2,\"fabricante2\")");
		conn.insertData("ex1", "Articulos", "VALUES (1,\"leche\", 3.99, 1)");
		conn.insertData("ex1", "Articulos", "VALUES (1,\"azucar\", 3.95, 1)");
		conn.getValues("ex1", "Fabricantes");
		conn.getValues("ex1", "Articulos");
		conn.closeConnection();
	}

}
