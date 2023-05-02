package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

}

@Override
public JSONObject login(AuthenticationRequest authreq) {
	JSONObject json = new JSONObject();

	try {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authreq.getUsername(), authreq.getHashed_password()));

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		if (userDetails != null) {

			final String jwt = jwtTokenUtil.generateToken(userDetails);


			JwtResponse jwtres = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
					userDetails.getEmail(), roles, jwtTokenUtil.extractExpiration(jwt).toString());

			return json.put("jwtresponse", jwtres);
		}
	} catch (BadCredentialsException ex) {
		json.put("status", "badcredentials");
	} catch (LockedException ex) {
		json.put("status", "LockedException");
	} catch (DisabledException ex) {
		json.put("status", "DisabledException");
	}

	return json;
}