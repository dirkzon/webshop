package webshop.service.gsonExclusionStrategies;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import webshop.service.models.ProductProperty;

public class IgnoreUnitIfNull implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getDeclaringClass() == ProductProperty.class && f.getName().equals("unit") && f.equals(null);
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
