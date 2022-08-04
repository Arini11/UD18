package ex4;

import connectionDB.ConnectionDB;

public class MainApp {

	public static void main(String[] args) {
		
		ConnectionDB ex4 = new ConnectionDB();
        //ABRIMOS CONEXION 
		ex4.openConnection();
		
        //INSERTAMOS UN DROP EN CASO DE HACER UNA PRUEBA Y NOS SALTE UN ERROR
		ex4.dropElement("DataBase", "ex4" );
		
        //CREAMOS LA BASE DE DATOS
        ex4.createDB("ex4");
        
		//CREAMOS LAS TABLAS DENTRO DE LA VASE DE DATOS
		ex4.createTable("ex4", "Peliculas", " (Codigo int PRIMARY KEY, Nombre nvarchar(100), CalificacionEdad int )");
        ex4.createTable("ex4", "Salas", "(Codigo int PRIMARY KEY, Nombre nvarchar(100), Pelicula int, constraint FK_Peliculas FOREIGN KEY (Pelicula) references Peliculas (Codigo)ON DELETE CASCADE)");

        //INSERTAMOS VALORES DENTRO DE LA TABLA PELICULAS
        ex4.insertData("ex4", " Peliculas ", " VALUES "
				+ "(1,'Star Wars - La Amenaza Fantasma', 16),"
				+ "(2,'El Señor de los Anillos - Las Dos Torres', 16),"
				+ "(3,'Jurassic Park 1', 16),"
				+ "(4,'Top Gun - Maverick', 16),"
				+ "(5,'The Avengers', 16)");
        //INSERTAMOS VALORES DENTRO DE LA TABLA SALAS
		ex4.insertData("ex4", " Salas ", " VALUES "
				+ "(1,' Sala1', 1),"
				+ "(2,' Sala2', 2),"
				+ "(3,' Sala3', 3),"
				+ "(4,' Sala4', 4),"
				+ "(5,' Sala5', 5)");
		
		//MOSTRAMOS LOS VALORES INTRODUCIDOS EN LA TABLA SALAS
		ex4.getValues("ex4", "Salas");
		
		//MOSTRAMOS LOS VALORES INTRODUCIDOS EN LA TABLA PELICULAS
		ex4.getValues("ex4", "Peliculas");
		
		//ELIMINAMOS LA PELICULA STAR WARS, YA QUE INDICAMOS QUE ELIMINE EL ID 1 DENTRO DE LA TABLA PELICULAS
		ex4.deleteRecord("Peliculas", "Codigo", "1");
		
		//CERRAMOS CONEXION
		ex4.closeConnection();
        
        }
}
