package Dev.Miniprojectws.config.security.utils;


import Dev.Miniprojectws.config.security.bean.CustomPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomUserAuthenticationConverter implements UserAuthenticationConverter {

	private final String EMAIL = "email";
	private final String LOGIN =  "login";

	private Collection<? extends GrantedAuthority> defaultAuthorities;

	//@Autowired
	//private BranchRepository branchRepository;

	public void setDefaultAuthorities(String[] defaultAuthorities) {
		this.defaultAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(StringUtils.arrayToCommaDelimitedString(defaultAuthorities));
	}

	@Override
	public Map<String, ?> convertUserAuthentication(Authentication userAuthentication) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put(USERNAME, userAuthentication.getName());

		if (userAuthentication.getAuthorities() != null && !userAuthentication.getAuthorities().isEmpty())
			response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(userAuthentication.getAuthorities()));

		return response;
	}

	@Override
	public Authentication extractAuthentication(Map<String, ?> map) {
		//System.out.print("Map => " + map);
		if (map.containsKey(LOGIN)) {

			return new UsernamePasswordAuthenticationToken(new CustomPrincipal(
					1, map.get(LOGIN).toString()
					, map.get("login").toString()
					, getAuthorities(map)
					, null
					, null
					, "0300001"
					, "ธนาคารออมสิน สำนักงานใหญ่"
					, map
			), "N/A", getAuthorities(map));
		}
		return null;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
		if (!map.containsKey(AUTHORITIES))
			return defaultAuthorities;

		Object authorities = map.get(AUTHORITIES);

		if (authorities instanceof String)
			return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);

		if (authorities instanceof Collection)
			return AuthorityUtils.commaSeparatedStringToAuthorityList(
					StringUtils.collectionToCommaDelimitedString((Collection<?>) authorities));

		throw new IllegalArgumentException("Authorities must be either a String or a Collection");
	}

	private Integer[] getBranchIds(Object o){
		try{
			return Arrays.stream(o.toString().split(",")).map(s -> Integer.parseInt(s)).toArray(Integer[]::new);
		}catch (Exception e){}
		return null;
	}

	private String[] getTaxIds(Object o){
		try{
			return Arrays.stream(o.toString().split(",")).map(s -> s).toArray(String[]::new);
		}catch (Exception e){}
		return null;
	}
}
