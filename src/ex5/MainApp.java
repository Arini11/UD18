package ex5;
import connectionDB.ConnectionDB;

public class MainApp {

	public static void main(String[] args) {
		
		ConnectionDB ex5 = new ConnectionDB();
        //ABRIMOS CONEXION 
		ex5.openConnection();
		
        //INSERTAMOS UN DROP EN CASO DE HACER UNA PRUEBA Y NOS SALTE UN ERROR
		ex5.dropElement("DataBase", "ex5" );
		
        //CREAMOS LA BASE DE DATOS
        ex5.createDB("ex5");
        
        //CREAMOS LAS TABLAS DENTRO DE LA BASE DE DATOS
        ex5.createTable("ex5", "Despachos", "(Numero int PRIMARY KEY, Capacidad int)");
        ex5.createTable
        ("ex5", "Directores", "(DNI varchar(8) PRIMARY KEY, NomApels nvarchar (255), DNIJefe varchar (8), Despacho int, CONSTRAINT FK_2 FOREIGN KEY (DNIJefe) REFERENCES Directores(DNI), CONSTRAINT FK_1 FOREIGN KEY (Despacho) REFERENCES Despachos(Numero))");
        
     	//INSERTAMOS VALORES DENTRO DE LAS TABLAS
        ex5.insertData("ex5", " Despachos ", " VALUES "
				+ "(1,150),"
        		+ "(2,250),"
				+ "(3,400),"
        		+ "(4,500),"
				+ "(5,750)");
        ex5.insertData("ex5", " Directores ", " VALUES "
				+ "('1234567P' , 'Juan Garcia' , '8877441A', 1),"
				+ "('8295759F' , 'Sara Marin' , '8877442A', 5),"
				+ "('1100464R' , 'Pablo Sanchez' , '8877443A', 3),"
				+ "('7734354Z' , 'Sergio Simpson', '8877444A', 2),"
				+ "('0011009S' , 'Carlos Ramirez', '8877445A', 4)");
        
        //MOSTRAMOS LOS VALORES INTRODUCIDOS EN LA TABLA DESPACHOS
      	ex5.getValues("ex5", "Despachos");
      		
      	//MOSTRAMOS LOS VALORES INTRODUCIDOS EN LA TABLA DIRECTORES
      	ex5.getValues("ex5", "Directores");
      		
    		
    	//CERRAMOS CONEXION
    	ex5.closeConnection();
		
	}

}
