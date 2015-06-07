package spms.controls;

import java.util.Map;

/**
 * Created by 1001235 on 15. 5. 26..
 */
public interface Controller {
    String execute(Map<String, Object> model) throws Exception;
}
