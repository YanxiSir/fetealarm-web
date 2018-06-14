package com.yanxisir.fetealarm.service;

import com.alibaba.fastjson.JSON;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * @author YanxiSir
 * @since 2018/5/16
 */
@Slf4j
@Service
public class FreeMarkerService {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    public String html(String templateName, Object model) {

        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            log.error("FreeMarker-html渲染失败，template:" + templateName + " ; model" + JSON.toJSONString(model), e);
        }
        return "";
    }

    public Template template(String templateName) {
        try {
            return freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
        } catch (Exception e) {
            log.error("FreeMarker-template渲染失败，template:" + templateName, e);
        }
        return null;
    }

    public String html(Template template, Object model) {
        try {
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            log.error("FreeMarker-html2渲染失败， model" + JSON.toJSONString(model), e);
        }
        return "";
    }
}
