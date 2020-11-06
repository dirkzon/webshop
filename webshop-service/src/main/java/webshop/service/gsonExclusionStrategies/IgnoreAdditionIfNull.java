package webshop.service.gsonExclusionStrategies;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import webshop.service.models.Address;

public class IgnoreAdditionIfNull implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getDeclaringClass() == Address.class && f.getName().equals("houseNumberAddition") && f.equals(null);
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
