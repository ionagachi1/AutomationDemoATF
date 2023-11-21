package actions;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stepDefs.AdminSteps;

import java.util.Map;

public class ValidateAPI {

    private static final Logger logger = LoggerFactory.getLogger(AdminSteps.class);

    public boolean compareJsonObject(Map<String, String> expectedLoginDetails, JsonObject userJsonObject){

        boolean contentMatches = true;
        for (Map.Entry<String, String> entry : expectedLoginDetails.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (!userJsonObject.has(key) || !userJsonObject.get(key).getAsString().equals(value)) {
                contentMatches = false;
                logger.info("Validation Failed: Validated key: {} - expected: '{}', actual: '{}'", key, value, userJsonObject.get(key).getAsString());
                break;
            }
            logger.info("Validation Passed: Validated key: {} - expected: '{}', actual: '{}'", key, value, userJsonObject.get(key).getAsString());
        }
        return contentMatches;
    }

    public String getToken(JsonObject actualBodyJson){
        if(actualBodyJson.has("token") && !actualBodyJson.get("token").getAsString().isEmpty())
        {
            return actualBodyJson.get("token").getAsString();
        }
        return null;
    }
}
