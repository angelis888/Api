public class Token {

    private static String SECRET = "c3bff416-993f-4760-9275-132b00256944";

    public static String get(String name, String value) throws UnsupportedEncodingException {
        return JWT.create()
                .withIssuer("auth0")
                .withClaim(name, value)
                .withClaim("random", String.valueOf(UUID.randomUUID()))
                .withExpiresAt(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)))
                .sign(Algorithm.HMAC256(Token.SECRET));
    }

    public static DecodedJWT verify(String token) throws JWTVerificationException, UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(Token.SECRET))
                .withIssuer("auth0")
                .acceptExpiresAt(4)
                .build();

        return verifier.verify(token);
    }

}