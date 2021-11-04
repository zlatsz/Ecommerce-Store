package sistersart.web.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@Component
public class BackGroundImgInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String url = "";
        LocalDate localDate = LocalDate.now();
        int day = localDate.getDayOfWeek().getValue();

        if (day > 0 && day <= 5) {
            url = "/images/logo-bg.png";
        } else if (day == 7 || day == 6) {
            url = "/images/oil.jpg";
        }

        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        } else {
            if (handler instanceof HandlerMethod) {
                modelAndView
                        .addObject("url", url);
            }
        }
    }
}
