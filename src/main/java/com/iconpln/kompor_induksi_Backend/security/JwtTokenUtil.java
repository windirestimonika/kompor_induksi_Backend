package com.iconpln.kompor_induksi_Backend.security;

import com.iconpln.kompor_induksi_Backend.entity.DetailUserEntity;
import com.iconpln.kompor_induksi_Backend.entity.User;
import com.iconpln.kompor_induksi_Backend.exception.ExpiredTooLongException;
import com.iconpln.kompor_induksi_Backend.model.JwtDto;
import com.iconpln.kompor_induksi_Backend.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = 7997340878020604616L;
    private static final long JWT_TOKEN_VALIDITY = 8 * 60 * 60;
    private static final long REFRESH_TOKEN_VALIDITY = 24 * 60 * 60;

    @Autowired
    UserService userService;
    @Autowired
    UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Boolean checkTokenType(String token, String type) {
        Map<String, Object> claims = getClaims(token);
        return String.valueOf(claims.get("type")).equals(type);
    }

    public LocalDateTime getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public String getIdFromToken(String token) {
        return getClaimFromToken(token, Claims::getId);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        SignatureAlgorithm sa = SignatureAlgorithm.HS256;
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), sa.getJcaName());
        return Jwts.parser().setSigningKey(secretKeySpec).parseClaimsJws(token).getBody();
    }

    public Map<String, Object> getClaims(String token){
        return getAllClaimsFromToken(token);
    }

    private Boolean isTokenExpired(String token){
        final LocalDateTime expirationDate = getExpirationDateFromToken(token);
        return expirationDate.isBefore(LocalDateTime.now());
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        Optional<User> optionalUser = userService.findByUsername(userDetails.getUsername());
        claims.put("type", "access");
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            DetailUserEntity detailUserEntity = user.getDetailUserEntity();
            claims.put("fullname", detailUserEntity.getNama());
            claims.put("nip", detailUserEntity.getNip());
            claims.put("id_detail_user", detailUserEntity.getId());
            claims.put("id_user", user.getId());
        }
        return doGenerateToken(claims, userDetails.getUsername(), JWT_TOKEN_VALIDITY);
    }

    public String generateRefreshToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("type", "refresh");
        return doGenerateToken(claims, userDetails.getUsername(), REFRESH_TOKEN_VALIDITY);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, long validity) {
        SignatureAlgorithm sa = SignatureAlgorithm.HS256;
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), sa.getJcaName());
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity * 1000))
                .signWith(sa, secretKeySpec).compact();
    }

    public Boolean validateToken(String token){
        try{
            return (!isTokenExpired(token) && validateSignature(token));
        } catch (Exception e){
            return false;
        }
    }

    private Boolean validateSignature(String token){
        String[] chunks = token.split("\\.");
        SignatureAlgorithm sa = SignatureAlgorithm.HS256;
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), sa.getJcaName());
        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(SignatureAlgorithm.HS256, secretKeySpec);
        return validator.isValid(chunks[0]+"."+chunks[1], chunks[2]);
    }

    public JwtDto refreshToken(String refreshToken){
        try {
            if(refreshToken.startsWith("Bearer")){
                refreshToken = refreshToken.substring(7);
                Map<String, Object> claims = getClaims(refreshToken);
                String username = String.valueOf(claims.get("sub"));
                if(!isTokenExpired(refreshToken) && checkTokenType(refreshToken, "refresh")){
                    JwtDto jwtDto = new JwtDto();
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    jwtDto.setJwtToken(generateToken(userDetails));
                    jwtDto.setRefreshToken(generateRefreshToken(userDetails));
                    return jwtDto;
                } else {
                    throw new ExpiredTooLongException("Token expired too long");
                }
            } else {
                throw new RuntimeException("Token invalid");
            }
        } catch (Exception e){
            throw new ExpiredTooLongException("Token expired too long");
        }
    }
}
