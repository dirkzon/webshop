package webshop.service.gsonExclusionStrategies;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import webshop.service.models.Retailer;

public class IgnoreRetailer implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return clazz == Retailer.class;
    }
}
