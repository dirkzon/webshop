package webshop.service.gsonExclusionStrategies;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import webshop.service.models.Product;

import java.lang.reflect.Type;

public class ExcludeProduct implements JsonSerializer<Product> {

    @Override
    public JsonElement serialize(Product src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
