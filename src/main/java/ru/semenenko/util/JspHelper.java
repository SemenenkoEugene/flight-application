package ru.semenenko.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {
    private static final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";

    public String getPath(String jsp) {
        return JSP_FORMAT.formatted(jsp);
    }

}
