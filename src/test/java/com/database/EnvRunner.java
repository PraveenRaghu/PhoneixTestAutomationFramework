package com.database;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Dotenv dotenv = Dotenv.load();
		String data=dotenv.get("DB_PASSWORD");
		System.out.println(data);

	}

}
