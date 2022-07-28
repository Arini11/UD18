package connectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConnectionDB {

	private Connection conexion;

	public void openConnection(String name) {

		// Creo que se tiene que cambiar en url base dades, mysql por el nombre que
		// tengamos de la bbdd ?

		String urlBaseDades = "jdbc:mysql://localhost:3306?useTimezone=true&serverTimezone=UTC";
		String user = "arnau";
		String pass = "arnau";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(urlBaseDades, user, pass);
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
			// no ho agafa tal com esta ara (Class.forName)
			// Logger.getLogger((MySQL.class.getName()).log(Level.SEVERE,null,ex);
			System.out.println(ex);
		}
	}

	public void createDB(String name) {
		try {
			if (conexion == null)
				openConnection(name);
			String Query = "CREATE DATABASE " + name;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			conexion.close();
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
			System.out.println("Tabla creada con exito!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Error creando tabla.");
		}
	}

	// INSERTAR DATOS EN TABLAS MYSQL

	public void insertData(String db, String nombre_tabla, String campos) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO " + nombre_tabla + " " + campos;

			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Datos almacenados correctamente");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
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
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);

			while (resultSet.next()) {
				for(int i=1;i>0;i++) {
					try {
						resultSet.getString(i);
					}catch(Exception e) {
						break;
					}
					System.out.print(resultSet.getString(i) + " | ");
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	//METODO ELIMINA VALORES DE DB
	
	public void deleteRecord(String nombre_tabla, String ID) {
		try {
			String Query = "DELETE FROM " + nombre_tabla + "WHERE ID " + ID + "\"";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
		}
	};
}
