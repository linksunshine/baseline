package com.linksunshine.baseline.server.web.controller;

import com.linksunshine.baseline.server.web.commons.utils.StringEscapeEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ucmed on 2017/3/20.
 */
public abstract class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * �Զ�ת���������͵��ֶθ�ʽ
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

        /**
         * ��ֹXSS����
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }

}
