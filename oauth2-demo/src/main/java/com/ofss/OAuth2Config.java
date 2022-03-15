package com.ofss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
   private String clientid = "guru";
   private String clientSecret = "guru";
   private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
   		"MIIEowIBAAKCAQEAyldVhUBjuSRBAfAKySWVgNHbfOGMfCnCyu3OMT/CpcAz0M+I\r\n" + 
   		"ThWcSkCmdDBeu/GRqenfi9Q3vrHHuXKvrAiI0MCfkI3oyTuD9wbuMf81QXFn2bC0\r\n" + 
   		"41NiTXs1gp76zD9Z8yB89fjfIWJxu1zhIpT/Snhou30glXaMDfFpQWtLxonlOev8\r\n" + 
   		"Y6UddHxg0biTW6wHsaaLl83jd5OPta/eEjqtKb0pQRuP+xBpvktELFF/6KuYox5U\r\n" + 
   		"rt1MMks7OeNPUNBHr15p258CiivyalpKeJ4MtZ1GJh1/xgEE7XrZ+HVBO7U61Gpy\r\n" + 
   		"hBsGP79j2mOD6PqH7LnAW8P9/iaBk7Ey+BEw+wIDAQABAoIBAFl9QLSbYr+Kejna\r\n" + 
   		"KVsHBVUg+3sC4jgz3BQWEuCTO4pFNGWAs98dDa+FYlj2358t2ueUKStVo4XyaMvr\r\n" + 
   		"Hiju6VuyJaAwMyl06guCBC0Wmz4jYvPThF0aDtsHyAXKPE/DIQw3bSz4ce749CYS\r\n" + 
   		"kPvTfAk6sc9iKtwPvyeXgp7NTokywDqI/gNicAkvu0ledkDsGrMbdyZQMwL0y5MG\r\n" + 
   		"luyQt4gd4MX2yptEOrVjPLx8vWk4BDZpItYWP/YcqkHCuhzFmyCUYYROqAkmORWr\r\n" + 
   		"+KmoC+cr1afw5sosoybFIYWwvHnb6DvzNlH6asn0UF8QmMs9rxDtgt58tt9OuWrO\r\n" + 
   		"JNf/FUECgYEA7DWfIa3blmFtDKEWcQdu+46X+lQ/rOQa2rbBv53fmHlESNRZ2Ukb\r\n" + 
   		"qnRyGnlA9KR07TikL6gnk4GvPFmJ6u8Ehf8oonsMouKMYD/LmCtu4c6Zy5f/fABU\r\n" + 
   		"/MM+gvp7btE/Mwkuakbd1WT54ORhAlyES73/sJ9I4H2kcN1d0nMoXiECgYEA20tI\r\n" + 
   		"Q/6xUuW8PZiv6mRBv9xPVNPRHSfe/xBYgLF1tXrtk68CshNONVVmtvjjLtacj/Y5\r\n" + 
   		"qK0kZ9QMGVDqRqGDeaJLgUv9x0i+/CBkH1+BNFwWC6JUQWAyExq7l0Yq6Nplemj2\r\n" + 
   		"ySP/waVIeMz3kHMwV/IG8nmv4BeBkreDWPmr05sCgYEA2K6LzuJk2HuBBJ/VLVSL\r\n" + 
   		"W5pAEjQswVXyyIX0D+osDfvYRxL9ZRTqmx/8+AoZKxKd7GsUOl+ABpFlhfABU5F1\r\n" + 
   		"TdJ2nS5Rti4qZbS/+FSHf/79GEXT6RWI4OHeO1q51jt/RTr76CQgJTxe+Q2na/og\r\n" + 
   		"pjaiEozb1ouLSLnY8lAPTOECgYBZGoS5hBJ/EXPkP1czURtaHuVpHUv5ocq9LuIX\r\n" + 
   		"IIQoglNoT5Mz0h5UE2hR7l05GeCxqiWRgRCq04E7BCK9IkFuueAeIEd0ov1fKrYj\r\n" + 
   		"/UVdhhE7FvFPQwv1sUYJ2Gv1A1B6ZY3o+qSgBU2mL/pjijpZcbEa8xDgaAyxFbnb\r\n" + 
   		"/E6yAwKBgG0Fu7LmG8y7sebEGERsgLu9FnobEyoSNdh8AwH2GtwepZMtEOL5NTAY\r\n" + 
   		"5CFb5jYwMmskRAtBzvpbaMM8mRCHLpuN8Yw4OXMBOblsE6fVZufdeIic1v61vN/+\r\n" + 
   		"Y/TJ8UntsZc38K3I+BDAmWoRiP6LPH9oLCvUa3zzbFOwA92qqKVZ\r\n" + 
   		"-----END RSA PRIVATE KEY-----\r\n";
   
   private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n" + 
   		"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyldVhUBjuSRBAfAKySWV\r\n" + 
   		"gNHbfOGMfCnCyu3OMT/CpcAz0M+IThWcSkCmdDBeu/GRqenfi9Q3vrHHuXKvrAiI\r\n" + 
   		"0MCfkI3oyTuD9wbuMf81QXFn2bC041NiTXs1gp76zD9Z8yB89fjfIWJxu1zhIpT/\r\n" + 
   		"Snhou30glXaMDfFpQWtLxonlOev8Y6UddHxg0biTW6wHsaaLl83jd5OPta/eEjqt\r\n" + 
   		"Kb0pQRuP+xBpvktELFF/6KuYox5Urt1MMks7OeNPUNBHr15p258CiivyalpKeJ4M\r\n" + 
   		"tZ1GJh1/xgEE7XrZ+HVBO7U61GpyhBsGP79j2mOD6PqH7LnAW8P9/iaBk7Ey+BEw\r\n" + 
   		"+wIDAQAB\r\n" + 
   		"-----END PUBLIC KEY-----";

   @Autowired
   @Qualifier("authenticationManagerBean")
   private AuthenticationManager authenticationManager;
   
   @Bean
   public JwtAccessTokenConverter tokenEnhancer() {
      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
      converter.setSigningKey(privateKey);
      converter.setVerifierKey(publicKey);
      return converter;
   }
   @Bean
   public JwtTokenStore tokenStore() {
      return new JwtTokenStore(tokenEnhancer());
   }
   @Override
   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
      .accessTokenConverter(tokenEnhancer());
   }
   @Override
   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
   }
   @Override
   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      clients.
      inMemory()
      .withClient(clientid)
      .secret(clientSecret)
      .scopes("read", "write")
      .authorizedGrantTypes("password", "refresh_token")
      .accessTokenValiditySeconds(20); 
//      .refreshTokenValiditySeconds(20000); // after every 20 sec, the new token will be generated

   }
} 