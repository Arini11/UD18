package ex1;

import connectionDB.ConnectionDB;

public class MainApp {
	
	private static ConnectionDB conn = null;

	public static void main(String[] args) {
		conn = new ConnectionDB();
		conn.createDB("ex1");
		conn.createTable("ex1", "Fabricantes", "(codigo int PRIMARY KEY, nombre nvarchar(100))");
		conn.createTable("ex1", "Articulos", " (codigo int PRIMARY KEY, nombre nvarchar(100), precio double, fabricante int,"
				+ "constraint FK_Fabricante FOREIGN KEY (Fabricante) references Fabricantes (codigo))");
		conn.insertData("ex1", "Fabricantes", "VALUES(1,'Sony'),"
				+ "(2,'Creative Labs'),"
				+ "(3,'Hewlett-Packard'),"
				+ "(4,'Iomega'),"
				+ "(5,'Fujitsu'),"
				+ "(6,'Winchester')");
		conn.insertData("ex1", "Articulos", "VALUES(1,'Hard drive',240,5),"
				+ "(2,'Memory',120,6),"
				+ "(3,'ZIP drive',150,4),"
				+ "(4,'Floppy disk',5,6),"
				+ "(5,'Monitor',240,1),"
				+ "(6,'DVD drive',180,2),"
				+ "(7,'CD drive',90,2),"
				+ "(8,'Printer',270,3),"
				+ "(9,'Toner cartridge',66,3),"
				+ "(10,'DVD burner',180,2)");
		conn.getValues("ex1", "Fabricantes");
		conn.getValues("ex1", "Articulos");
		conn.closeConnection();
	}

}
