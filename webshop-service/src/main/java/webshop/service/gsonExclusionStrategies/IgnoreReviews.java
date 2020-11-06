package webshop.service.gsonExclusionStrategies;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class IgnoreReviews implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getName().equals("reviews");
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
