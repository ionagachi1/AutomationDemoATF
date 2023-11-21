package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static final Logger logger = LoggerFactory.getLogger(ScenarioContext.class);
    private static ScenarioContext instance;
    private static Map<String, Object> contextMap;

    private ScenarioContext() {
        contextMap = new HashMap<>() {
        };
    }

    public static ScenarioContext getInstance() {
        if (instance == null) {
            instance = new ScenarioContext();
            logger.info("ScenarioContext initialized successfully");
        }
        return instance;
    }

    public void setContextObject(String key, Object value) {
        contextMap.put(key, value);
    }

    public <T> T getContextObject(String key) {
        return (T) contextMap.get(key);
    }

    public static void clearContext() {
        if (instance != null && !contextMap.isEmpty()) {
            contextMap.clear();
            logger.info("ContextMap cleared successfully");
        }
    }
}