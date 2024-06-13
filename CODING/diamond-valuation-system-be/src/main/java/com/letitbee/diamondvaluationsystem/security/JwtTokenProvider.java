//package com.letitbee.diamondvaluationsystem.security;
//
//import com.letitbee.diamondvaluationsystem.exception.APIException;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//
//    @Value("${app.jwt-secret}")
//    private String jwtSecret;
//    @Value("${app-jwt-expiration-milliseconds}")
//    private long jwtExpirationDate;
//
//    //generate JWT token
//
//    public String generateToken(Authentication authentication){
//
//        String username = authentication.getName();
//
//        Date currentDate = new Date();
//
//        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
//
//        String token = Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(currentDate)
//                .setExpiration(expireDate)
//                .signWith(key())
//                .compact();
//
//        return token;
//    }
//
//    private Key key(){
//        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
//    }
//
//    //get username from JWT token
//
//    public String getUsername(String token){
//        return Jwts.parserBuilder()
//                .setSigningKey(key())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    //validate JWT token
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(key())
//                    .build()
//                    .parse(token);
//            return true;
//        } catch (MalformedJwtException e) {
//            throw new APIException(HttpStatus.BAD_REQUEST,"Invalid JWT token");
//        } catch (ExpiredJwtException e) {
//            throw new APIException(HttpStatus.BAD_REQUEST,"JWT token is expired");
//        } catch (UnsupportedJwtException e){
//            throw new APIException(HttpStatus.BAD_REQUEST,"JWT token is unsupported");
//        } catch (IllegalArgumentException e){
//            throw new APIException(HttpStatus.BAD_REQUEST,"JWT claims string is empty");
//        }
//    }
//}
