package spms.controls;

import spms.annotation.Component;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by 1001235 on 15. 5. 26..
 */
@Component("/auth/logout.do")
public class LogoutController implements Controller {
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        HttpSession session = (HttpSession) model.get("session");
        session.invalidate();

        return "redirect:login.do";
    }

}
