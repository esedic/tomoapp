package com.mood.tomoapp.cache;

import java.lang.reflect.Method;

import com.mood.tomoapp.repos.ActiveRepository;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class ActiveableKeyGenerator implements KeyGenerator {

    public Object generate(Object target, Method method, Object... params) {
        Object[] keyParams = new Object[params.length + 1];
        keyParams[0] = getActiveableInterface(target.getClass());
        System.arraycopy(params, 0, keyParams, 1, params.length);
        return new SimpleKey(keyParams);
    }

    protected Class<?> getActiveableInterface(Class<?> clazz) {
        if (clazz == Object.class) {
            throw new IllegalStateException("No Activeable interface!");
        }
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> ifc : interfaces) {
            if (ActiveRepository.class.isAssignableFrom(ifc)) {
                return ifc;
            }
        }
        return getActiveableInterface(clazz.getSuperclass());
    }
}
