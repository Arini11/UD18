package ex2;

import connectionDB.ConnectionDB;

public class MainApp {

	public static void main(String[] args) {
		ConnectionDB conn = new ConnectionDB();
		conn.createDB("ex2");
		conn.openConnection();
		conn.createTable("ex2", "Departamentos", "(codigo int PRIMARY KEY, nombre varchar(100), presupuesto int)");
		conn.createTable("ex2", "Empleados", "(dni varchar(9) PRIMARY KEY, nombre varchar(100), apellidos varchar(255), departamento int,"
				+ "constraint FK_Departamento FOREIGN KEY (Departamento) REFERENCES Departamentos(codigo))");
		conn.insertData("ex2", "Departamentos", "VALUES(1, 'Marqueting', 999)");
		conn.insertData("ex2", "Departamentos", "VALUES(2, 'Contabilidad', 999)");
		conn.insertData("ex2", "Departamentos", "VALUES(3, 'Informatica', 999)");
		conn.insertData("ex2", "Departamentos", "VALUES(4, 'Oficina Tecnica', 2500)");
		conn.insertData("ex2", "Departamentos", "VALUES(5, 'Recursos Humanos', 500)");
		conn.insertData("ex2", "Empleados", "VALUES('39933791A', 'Arnau', 'Mas', 1)");
		conn.insertData("ex2", "Empleados", "VALUES('39933791B', 'Cristhian', 'Requena', 2)");
		conn.insertData("ex2", "Empleados", "VALUES('39933791C', 'Juan', 'Marin', 1)");
		conn.insertData("ex2", "Empleados", "VALUES('39933791D', 'Jone', 'Joana', 2)");
		conn.insertData("ex2", "Empleados", "VALUES('39933791E', 'Jaume', 'Marti', 4)"); 
		conn.closeConnection();
	}

}
