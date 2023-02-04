package com.hiio.backend;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@SpringBootTest
class BackendApplicationTests {

	@Test
	void createJwt() {
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		System.out.println(key);
		Map<String,Object> a= new HashMap<>();
		a.put("user","d");

		Claims kk = Jwts.claims(a).setId("s");
		Date date = new Date();
		String jws = Jwts
						.builder()
						.setClaims(kk)
						.setExpiration(new Date(date.getTime()+102020))
						.signWith(key)
						.compact();
		System.out.println(jws);
		Jws bb = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws);
		Claims aa=  (Claims) bb.getBody();
		
		System.out.println(bb);
		System.out.println(aa.get("user"));
		System.out.println(aa.getId());

	}

}
