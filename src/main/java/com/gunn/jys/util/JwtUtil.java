package com.gunn.jys.util;

import com.gunn.jys.security.verifier.MACVerifierExtended;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    //过期时间 单位分钟
    private static final long EXPIRE_TIME = 30;

    //由uuid随机生成的secret
    private static final String SECRET = "e25395f4646a406f9b1754d394c8450b";

    //JWT中的签发者
    private static final String ISSUER = "gjhJys";

    /**
     * 生成token
     * @param userId
     * @return
     */
    public static String createToken(Object userId) {
        try {
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.issuer(ISSUER). //签发者
                    subject(userId.toString()). //jwt所面向的用户
                    issueTime(new Date()). //jwt签发时间
                    notBeforeTime(new Date()). //在定义时间之前该jwt都不可用
                    expirationTime(DateUtils.addMinutes(new Date(), 30)). //token过期时间
                    jwtID(UUID.randomUUID().toString());

            JWTClaimsSet claimsSet = builder.build();
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
            Payload payload = new Payload(claimsSet.toJSONObject());
            JWSSigner signer = new MACSigner(SECRET);
            JWSObject jwsObject = new JWSObject(header, payload);
            jwsObject.sign(signer);
            return jwsObject.serialize();
        } catch (JOSEException ex) {
            return null;
        }
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static boolean validateToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifierExtended(SECRET, signedJWT.getJWTClaimsSet());
            return signedJWT.verify(verifier);
        } catch (ParseException ex) {
            return false;
        } catch (JOSEException ex) {
            return false;
        }
    }

    /**
     * 从token中获取sub信息
     * @param token
     * @return
     * @throws ParseException
     */
    public static String getFromToken(String token) throws ParseException {
        JWSObject jwsObject = JWSObject.parse(token);
        String decrypted = jwsObject.getPayload().toString();

        try(JsonReader jr = Json.createReader(new StringReader(decrypted))) {
            JsonObject jsonObject = jr.readObject();
            String userId = jsonObject.getString("sub", null);
            return userId;
        }
    }

    public static String getFromTokenInfo(String token) {
        String json = null;
        try {
            json = getFromToken(token);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return json;
    }
}
