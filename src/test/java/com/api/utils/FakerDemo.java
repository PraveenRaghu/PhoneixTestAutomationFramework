package com.api.utils;

import com.github.javafaker.Faker;

public class FakerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Faker faker = new Faker();
		String name=faker.name().firstName();
		System.out.println(name);
	}

}
