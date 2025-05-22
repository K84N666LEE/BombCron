package com.src.common.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;

public class LogUtil {
    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT); // JSON 줄바꿈용

    private static final XStream xstream = new XStream();

    // JSON 로그 출력
    public static void logJson(Logger logger, String prefix, Object obj) {
        try {
            String prettyJson = mapper.writeValueAsString(obj);
            logger.info("{}\n{}", prefix, prettyJson);
        } catch (Exception e) {
            logger.error("JSON 직렬화 실패: {}", e.getMessage());
        }
    }

    // XML 로그 출력
    public static void logXml(Logger logger, String prefix, Object obj) {
        try {
            String xml = xstream.toXML(obj);
            logger.info("{}\n{}", prefix, xml);
        } catch (Exception e) {
            logger.error("XML 직렬화 실패: {}", e.getMessage());
        }
    }
}