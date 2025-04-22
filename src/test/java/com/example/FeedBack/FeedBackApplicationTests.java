package com.example.FeedBack;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class FeedBackApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void hashPassword(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "admin123";
		String hashedPassword = encoder.encode(rawPassword);
		System.out.println("Hashed Password: "+ hashedPassword);
		System.out.print(encoder.matches(rawPassword , hashedPassword)  );
	}

	@Test
	public void verifyPassword() {
		String rawPssword = "suhas123";
		String hshedPassword = "$2a$10$OLbth9PghmbESC4tOy3BgOjScxj4KrjKAC3Er2Zy2XWSBd070Tdce";
		System.out.println(hshedPassword.length());

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.matches(rawPssword, hshedPassword));
	}

}
