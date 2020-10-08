package Dev.Miniprojectws.config.security.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CustomPrincipal implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String username;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private Integer[] branchIds;
    private String[] rolenames;
    private String branchCode;
    private String branchName;
    private Map<String, ?> maps;
}
