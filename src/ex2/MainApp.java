package ex2;

import connectionDB.ConnectionDB;

public class MainApp {

	public static void main(String[] args) {
		ConnectionDB conn = new ConnectionDB();
		conn.createDB("ex2");
		conn.createTable("ex2", "Departamentos", "(codigo int PRIMARY KEY, nombre varchar(100), presupuesto int)");
		conn.createTable("ex2", "Empleados", "(dni varchar(9) PRIMARY KEY, nombre varchar(100), apellidos varchar(255), departamento int,"
				+ "constraint FK_Departamento FOREIGN KEY (Departamento) REFERENCES Departamentos(codigo))");
		conn.insertData("ex2", "Departamentos", "VALUES(14,'IT',65000),"
				+ "(37,'Accounting',15000),"
				+ "(59,'Human Resources',240000),"
				+ "(77,'Research',55000)");
		conn.insertData("ex2", "Empleados", "VALUES(123234877,'Michael','Rogers',14),(152934485,'Anand','Manikutty',14),"
				+ "(222364883,'Carol','Smith',37),(326587417,'Joe','Stevens',37),(332154719,'Mary-Anne','Foster',14),"
				+ "(332569843,'George','O Donnell',77),(546523478,'John','Doe',59),(631231482,'David','Smith',77),"
				+ "(654873219,'Zacary','Efron',59),(745685214,'Eric','Goldsmith',59),(845657233,'Luis','López',14),"
				+ "(845657245,'Elizabeth','Doe',14),(845657246,'Kumar','Swamy',14),(845657266,'Jose','Pérez',77)");
		conn.getValues("ex2", "Departamentos");
		conn.getValues("ex2", "Empleados");
		conn.closeConnection();
	}

}
