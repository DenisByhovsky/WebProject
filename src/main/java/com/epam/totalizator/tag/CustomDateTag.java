package com.epam.totalizator.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateTag extends TagSupport {

        @Override
        public int doStartTag() throws JspException {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String time = dateFormat.format(date);
            try {
                JspWriter out = pageContext.getOut();
                out.write(time);
            } catch (IOException e) {
                throw new JspException(e.getMessage());
            }
            return SKIP_BODY;
        }
        @Override
        public int doEndTag() throws JspException {
            return EVAL_PAGE;
        }
    }

