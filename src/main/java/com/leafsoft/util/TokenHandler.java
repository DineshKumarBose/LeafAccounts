package com.leafsoft.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leafsoft.model.LeafUser;

import java.security.Key;
import io.jsonwebtoken.*;

public final class TokenHandler {

	private static final String HMAC_ALGO = "HmacSHA256";
	private static final String SEPARATOR = ".";
	private static final String SEPARATOR_SPLITTER = "\\.";

	private final Mac hmac;

	public TokenHandler(byte[] secretKey) {
		try {
			hmac = Mac.getInstance(HMAC_ALGO);
			hmac.init(new SecretKeySpec(secretKey, HMAC_ALGO));
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new IllegalStateException("failed to initialize HMAC: " + e.getMessage(), e);
		}
	}

	public LeafUser parseUserFromToken(String token) {
		final String[] parts = token.split(SEPARATOR_SPLITTER);
		if (parts.length == 2 && parts[0].length() > 0 && parts[1].length() > 0) {
			try {
				final byte[] userBytes = fromBase64(parts[0]);
				final byte[] hash = fromBase64(parts[1]);

				boolean validHash = Arrays.equals(createHmac(userBytes), hash);
				if (validHash) {
					final LeafUser user = fromJSON(userBytes);
					//if (new Date().getTime() < LeafUser.getExpires()) {
						return user;
					//}
				}
			} catch (IllegalArgumentException e) {
				//log tempering attempt here
			}
		}
		return null;
	}

	public String createTokenForUser(LeafUser user) {
		byte[] userBytes = toJSON(user);
		byte[] hash = createHmac(userBytes);
		final StringBuilder sb = new StringBuilder(170);
		sb.append(toBase64(userBytes));
		sb.append(SEPARATOR);
		sb.append(toBase64(hash));
		return sb.toString();
	}

	private LeafUser fromJSON(final byte[] userBytes) {
		try {
			return new ObjectMapper().readValue(new ByteArrayInputStream(userBytes), LeafUser.class);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private byte[] toJSON(LeafUser user) {
		try {
			return new ObjectMapper().writeValueAsBytes(user);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e);
		}
	}

	private String toBase64(byte[] content) {
		return DatatypeConverter.printBase64Binary(content);
	}

	private byte[] fromBase64(String content) {
		return DatatypeConverter.parseBase64Binary(content);
	}

	// synchronized to guard internal hmac object
	private synchronized byte[] createHmac(byte[] content) {
		return hmac.doFinal(content);
	}
/*
	public static void main(String[] args) {
		Date start = new Date();
		byte[] secret = new byte[70];
		new java.security.SecureRandom().nextBytes(secret);

		TokenHandler tokenHandler = new TokenHandler(secret);
		for (int i = 0; i < 1000; i++) {
			final User user = new User(java.util.UUID.randomUUID().toString().substring(0, 8), new Date(
					new Date().getTime() + 10000));
			user.grantRole(UserRole.ADMIN);
			final String token = tokenHandler.createTokenForUser(user);
			final User parsedUser = tokenHandler.parseUserFromToken(token);
			if (parsedUser == null || parsedUser.getUsername() == null) {
				System.out.println("error");
			}
		}
		System.out.println(System.currentTimeMillis() - start.getTime());
	}
*/
	
	//Sample method to construct a JWT

	public String createJWT(String id, String issuer, String subject, long ttlMillis) {

	//The JWT signature algorithm we will be using to sign the token
	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	long nowMillis = System.currentTimeMillis();
	Date now = new Date(nowMillis);

	//We will sign our JWT with our ApiKey secret
	byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(AppResources.getInstance().getTokeSecret());
	Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	  //Let's set the JWT Claims
	JwtBuilder builder = Jwts.builder().setId(id)
	                                .setIssuedAt(now)
	                                .setSubject(subject)
	                                .setIssuer(issuer)
	                                .signWith(signatureAlgorithm, signingKey);

	 //if it has been specified, let's add the expiration
	if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	    Date exp = new Date(expMillis);
	    builder.setExpiration(exp);
	}

	 //Builds the JWT and serializes it to a compact, URL-safe string
	return builder.compact();

	}
	
	//Sample method to validate and read the JWT
	public Claims parseJWT(String jwt) {
	//This line will throw an exception if it is not a signed JWS (as expected)
	Claims claims = Jwts.parser()         
	   .setSigningKey(DatatypeConverter.parseBase64Binary(AppResources.getInstance().getTokeSecret()))
	   .parseClaimsJws(jwt).getBody();
	System.out.println("ID: " + claims.getId());
	System.out.println("Subject: " + claims.getSubject());
	System.out.println("Issuer: " + claims.getIssuer());
	System.out.println("Expiration: " + claims.getExpiration());
	return claims;
	}
}
