package com.vesta.config.security;

public class SecurityConstants {

    public static final String JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";
    public static final String TOKEN_HEADER = "X-Token-Authentication";
    public static final long EXPIRATION_TIME = 3_600_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String API_KEY_NAME = "JWT";
    public static final String REGEX_PATH= "/.*";
    public static final String ACCESS_DESCRIPTION = "accessEverything";
    public static final String SWAGGER_HEADER_AS = "Header";
    public static final String GLOBAL_SCOPE = "global";
    public static final String PATTERNS_PATH = "/**";
}

