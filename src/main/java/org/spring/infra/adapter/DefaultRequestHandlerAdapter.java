package org.spring.infra.adapter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jianglong.li
 * @date 2021-06-12 14:48
 **/
public class DefaultRequestHandlerAdapter extends SimpleControllerHandlerAdapter {
    public DefaultRequestHandlerAdapter() {
        super();
    }

    @Override
    public boolean supports(Object handler) {
        return super.supports(handler);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.handle(request, response, handler);
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return super.getLastModified(request, handler);
    }
}
