package ex1;

import connectionDB.ConnectionDB;

public class MainApp {

	public static void main(String[] args) {
		ConnectionDB conn = new ConnectionDB();
		conn.openConnection();
		conn.getValues("ud15_ex5","equipos");
	}

}
