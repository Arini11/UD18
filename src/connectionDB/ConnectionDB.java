package connectionDB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ConnectionDB {

	private Connection conexion;

	public void openConnection() {

		String user = "", password = "";
		String urlBaseDades = "jdbc:mysql://localhost:3306?useTimezone=true&serverTimezone=UTC";

		// Carregar user i password
		try (InputStream input = new FileInputStream("src/connectionDB/login.properties")) {
			Properties prop = new Properties();
			prop.load(input);
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(urlBaseDades, user, password);
			System.out.println("Server connected");

		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("No se ha podido conectar a la base de datos");
			System.out.println(ex);
		}
	}

	public void closeConnection() {
		try {
			conexion.close();
			System.out.println("Se ha finalizado la conexion con el servidor");
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	public void createDB(String name) {
		try {
			if (conexion == null)
				openConnection();
			String Query = "CREATE DATABASE " + name;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Se ha creado la base de datos " + name + " de forma exitosa.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error creando DB.");
		}
	}

	public void createTable(String db, String name, String campos) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE " + name + " " + campos;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla " + name + " creada con exito!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error creando tabla " + name + ".");
		}
	}

	// INSERTAR DATOS EN TABLAS MYSQL
	public void insertData(String db, String nombre_tabla, String campos) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "REPLACE INTO " + nombre_tabla + " " + campos; // TENER EN CUENTA QUE INSERTA I TAMBIEN HACE UPDATE SI JA EXISTE

			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Datos almacenados correctamente en la tabla "+nombre_tabla);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento de la tabla "+nombre_tabla);
		}
	}

	// OBTENER VALORES MYSQL
	public void getValues(String db, String nombre_tabla) {
        try {
            String Querydb = "USE " + db + ";";
            Statement stdb = conexion.createStatement();
            stdb.executeUpdate(Querydb);

            String Query = "SELECT * FROM " + nombre_tabla;
            Statement st = conexion.createStatement();
            ResultSet resultSet;
            resultSet = st.executeQuery(Query);

            showValuesTables(db, nombre_tabla, resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

	// METODO INTERNO QUE IMPRIME LOS DATOS - NOMBRE TABLE I NOMBRES COLUMNAS 
	private void showValuesTables(String db, String nombre_tabla, ResultSet resultSet) throws SQLException {
		int columnCount = resultSet.getMetaData().getColumnCount();
		
		System.out.println("\n"+nombre_tabla.toUpperCase());        
		
		ResultSet rs = conexion.getMetaData().getColumns(db, null, nombre_tabla, null);
		while(rs.next()) {
			String columnas = rs.getString(4); //la columna 4 es column_name 
			System.out.print(columnas+" ");
		}
		System.out.println("\n");
		
		while (resultSet.next()) {
		    for(int i=1;i<=columnCount;i++) {
		        System.out.print(resultSet.getString(i) + " | ");
		    }
		    System.out.println("");
		}
	}

	//METODO ELIMINA VALORES DE DB

	public void deleteRecord(String nombre_tabla, String nombre_ID, String ID) {
		try {
			String Query = "DELETE FROM " + nombre_tabla + " WHERE " + nombre_ID + "=" + ID;

			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
		}
	}
	
	// permite eliminar database o table si ya ha sido creada con errores
	public void dropElement(String tipoElement,String element) {
		try {
			String Query = "DROP " + tipoElement + " IF EXISTS " + element;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
		}
		
	}
}