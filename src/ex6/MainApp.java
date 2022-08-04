package ex6;
import connectionDB.ConnectionDB;
public class MainApp {

	public static void main(String[] args) {
		
		ConnectionDB ex6 = new ConnectionDB();
        //ABRIMOS CONEXION 
		ex6.openConnection();
		
        //INSERTAMOS UN DROP EN CASO DE HACER UNA PRUEBA Y QUE NOS SALTE UN ERROR
		ex6.dropElement("DataBase", "ex6" );
		
        //CREAMOS LA BASE DE DATOS
        ex6.createDB("ex6");
        
        //CREAMOS LAS TABLAS DENTRO DE LA BASE DE DATOS
        ex6.createTable(
        		"ex6", "Proveedores", "(ID char(4) PRIMARY KEY, Nombre nvarchar(100))");
        ex6.createTable(
        		"ex6", "Piezas", "(Codigo int PRIMARY KEY, Nombre nvarchar(100))");
        ex6.createTable(
        		"ex6", "Suministra", "(Codigo int PRIMARY KEY, ID char(4) PRIMARY KEY, Precio int, CONSTRAINT FK_1 FOREIGN KEY (Codigo) REFERENCES Piezas (Codigo), CONSTRAINT FK_2 FOREIGN KEY (idProveedor) REFERENCES Proveedores (ID)");
        
        //INSERTAMOS VALORES DENTRO DE LA TABLAS PROVEEDORES, PIEZAS Y SUMINISTRA
		
        ex6.insertData("ex6", " Proveedores ", " VALUES "
			+ "( 'PRV1' , 'Apple' ) , "
        		+ "( 'PRV2' , 'Microsoft' ) , "
			+ "( 'PRV3' , 'Xiaomi' ) , "
        		+ "( 'PRV4' , 'Nvidia' ) , "
			+ "( 'PRV5' , 'Intel' ) " );
		
	ex6.insertData("ex6", " Piezas ", " VALUES "
        		+ "( 101, 'Procesadores' ) , "
        		+ "( 202, 'Graficas' ) , "
        		+ "( 303, 'Tablets' ) , "
        		+ "( 404, 'Smarthwatch' ) , "
        		+ "( 505, 'Teclados') " );
        
        ex6.insertData("ex6", " Suministra ", " VALUES "
        		+ "( 101, 'PRV1', 80) , "
        		+ "( 202, 'PRV4', 200) , "
        		+ "( 303, 'PRV3', 250) , "
        		+ "( 404, 'PRV3', 179) , "
        		+ "( 505, 'PRV2', 40) " );
		
        //MOSTRAMOS LOS VALORES INTRODUCIDOS EN LA TABLA PROVEEDORES
  		ex6.getValues("ex6", "Proveedores");
  		
  		//MOSTRAMOS LOS VALORES INTRODUCIDOS EN LA TABLA PIEZAS
  		ex6.getValues("ex6", "Piezas");
  		
  		//MOSTRAMOS LOS VALORES INTRODUCIDOS EN LA TABLA SUMINISTRA
  		ex6.getValues("ex6", "Suministra");
  		
  		//ELIMINAMOS NVIDIA, YA QUE INDICAMOS QUE ELIMINE EL ID 4 DENTRO DE LA TABLA PROVEEDORES
		ex6.deleteRecord("Proveedores", "PRV4", "ID");
		
		//CERRAMOS CONEXION
		ex6.closeConnection();
	}

}
