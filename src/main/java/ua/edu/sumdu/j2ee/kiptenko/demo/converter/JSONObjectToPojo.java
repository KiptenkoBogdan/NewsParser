package ua.edu.sumdu.j2ee.kiptenko.demo.converter;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.edu.sumdu.j2ee.kiptenko.demo.model.Pojo;

@Component
public class JSONObjectToPojo implements Converter<JSONObject, Pojo> {

    private static final Logger logger = Logger.getLogger(JSONObjectToPojo.class);

    @Override
    public Pojo convert(JSONObject obj) {
        Pojo pojo = new Pojo();
        try{
            pojo.setTitle(obj.optString("title"));
            pojo.setDescription(obj.optString("description"));
            pojo.setUrl(obj.optString("url"));
            pojo.setAuthor(obj.optString("author"));
            pojo.setUrlToImage(obj.optString("urlToImage"));
        } catch (JSONException e){
            logger.error(e);
        }
        return pojo;
    }
}
