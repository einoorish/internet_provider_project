package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import constants.Fields;
import constants.Regex;
import constants.URL;
import controller.commands.RegistrationCommand;

public class RegistrationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(RegistrationFilter.class.getSimpleName());

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String login = request.getParameter(Fields.USER_LOGIN);
        final String password = request.getParameter(Fields.USER_PASSWORD);
        
        if(!login.matches(Regex.LOGIN_REGEX) || !password.matches(Regex.PASSWORD_REGEX)){
            request.setAttribute("invalidData", "true");
            LOGGER.error("Incorrect input data on registration page");
            request.getRequestDispatcher(URL.REDIRECT_HOME).forward(request, response);
            return;
        }
        
        RegistrationCommand register = new RegistrationCommand();
        
        request.getRequestDispatcher(register.execute(request)).forward(request, response);
    }

    @Override
    public void destroy() {

    }

    private void checkByRegex(String dataToCheck, String regex) throws Exception {
        if (!dataToCheck.matches(regex)) {
            throw new Exception();
        }
    }

}
