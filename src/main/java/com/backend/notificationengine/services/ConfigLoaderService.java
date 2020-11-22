package com.backend.notificationengine.services;


import com.backend.notificationengine.objects.Template;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigLoaderService {

    @Autowired
    MongoService mongoService;

    private List<Template> templates = new ArrayList<Template>();

    public void initConfig() {
        Bson filter = Filters.ne("id", -1);
        FindIterable<Document> getTemplates = mongoService.getData("template", filter);
        for(Document template: getTemplates) {
            templates.add(new Template(template.getInteger("id"), (String) template.get("template")));
        }
    }

    public String getTemplateFromId(int id) {
        Template template = null;
        for(int i=0;i<templates.size();i++) {
            if(templates.get(i).getId()==id) {
                template = templates.get(i);
                break;
            }
        }
        if(template==null)
            return "";
        return template.getTemplate();
    }

    public List<Template> getTemplates() {
        return templates;
    }
}
