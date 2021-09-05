package es.rubioric.rsp.session;

import static es.rubioric.rsp.RequestMappingConstants.REQUEST_MAPPING_LOGOUT;
import static es.rubioric.rsp.RequestMappingConstants.REQUEST_MAPPING_SHOW_PLAYED_ROUNDS;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 *<p>Logout Controller.</p>
 *
 *@author Ricardo Rubio
 *
 */
@Controller
public class LogoutController {

  @GetMapping(REQUEST_MAPPING_LOGOUT)
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:" + REQUEST_MAPPING_SHOW_PLAYED_ROUNDS;
  }
}
