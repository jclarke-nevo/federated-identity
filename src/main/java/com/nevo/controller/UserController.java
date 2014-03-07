package com.nevo.controller;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.security.cert.CertificateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.nevo.domain.User;
import com.nevo.service.IUserService;
import com.nevo.viewModel.UserVm;

/**
 * UserController handles non-API requests (rendering HTML results)
 * 
 * @author John C
 * 
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listAllUsers() {
		List<User> users = userService.findAll();
		List<UserVm> userModels = new ArrayList<UserVm>();
		for (User user : users) {
			userModels.add(new UserVm(user));
		}
		ModelAndView mv = new ModelAndView("/users/index", "UserList", userModels);
		return mv;
	}

	public static class TokenResponse {
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((access_token == null) ? 0 : access_token.hashCode());
			result = prime * result + expires_in;
			result = prime * result
					+ ((id_token == null) ? 0 : id_token.hashCode());
			result = prime * result
					+ ((refresh_token == null) ? 0 : refresh_token.hashCode());
			result = prime * result
					+ ((token_type == null) ? 0 : token_type.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TokenResponse other = (TokenResponse) obj;
			if (access_token == null) {
				if (other.access_token != null)
					return false;
			} else if (!access_token.equals(other.access_token))
				return false;
			if (expires_in != other.expires_in)
				return false;
			if (id_token == null) {
				if (other.id_token != null)
					return false;
			} else if (!id_token.equals(other.id_token))
				return false;
			if (refresh_token == null) {
				if (other.refresh_token != null)
					return false;
			} else if (!refresh_token.equals(other.refresh_token))
				return false;
			if (token_type == null) {
				if (other.token_type != null)
					return false;
			} else if (!token_type.equals(other.token_type))
				return false;
			return true;
		}

		public String getAccess_token() {
			return access_token;
		}

		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}

		public String getToken_type() {
			return token_type;
		}

		public void setToken_type(String token_type) {
			this.token_type = token_type;
		}

		public String getRefresh_token() {
			return refresh_token;
		}

		public void setRefresh_token(String refresh_token) {
			this.refresh_token = refresh_token;
		}

		public int getExpires_in() {
			return expires_in;
		}

		public void setExpires_in(int expires_in) {
			this.expires_in = expires_in;
		}

		public String getId_token() {
			return id_token;
		}

		public void setId_token(String id_token) {
			this.id_token = id_token;
		}

		private String access_token;
		private String token_type;
		private String refresh_token;
		private int expires_in;
		private String id_token;
	}

	@RequestMapping(value = "/passwords")
	public Object getUserPasswords(@RequestParam(required = false) String code) {
		// This code would go in a @PreAuthorize handler etc.
		if (code == null || code.length() == 0) {
			return "redirect:https://localhost:9031/as/authorization.oauth2?"
					+ "scope=openid&response_type=code&client_id=TestRP&redirect_uri=https://openidconnect.nevo.com:8443/FederatedIdentity/users/passwords";
		}

		// Fetch tokens
		RestTemplate client = new RestTemplate();
//		client.getMessageConverters().add(
//				new MappingJacksonHttpMessageConverter());
//		client.getMessageConverters().add(new StringHttpMessageConverter());

		String url = "https://localhost:9031/as/token.oauth2";
		MultiValueMap<String, String> form =
				 new LinkedMultiValueMap<String, String>(); 
		form.add("grant_type", "authorization_code"); 
		form.add("code", code);
		form.add("client_id",  "TestRP");
		form.add("client_secret", "abc");
		form.add("redirect_uri", "https://openidconnect.nevo.com:8443/FederatedIdentity/users/passwords");
		Object uriVariables = null;
		trustSelfSignedSSL();
		TokenResponse tokenResponse = client.postForObject(url, form,
				TokenResponse.class, uriVariables);

		List<User> users = userService.findAll();
		List<UserVm> userModels = new ArrayList<UserVm>();
		for (User user : users) {
			userModels.add(new UserVm(user));
		}
		ModelAndView mv = new ModelAndView("/users/index", "UserList",
				userModels);
		mv.addObject("IdToken", tokenResponse.id_token);
		return mv;
	}

	public static void trustSelfSignedSSL() {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {

				public void checkClientTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLContext.setDefault(ctx);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
