package com.yoco.taskHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.tasks.v2.*;
import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TaskCreator {

    public static void createTask(){

        log.info(" came inside createTask ");

        CloudTasksClient client;

        {
            try {

                client = CloudTasksClient.create();

                String queuePath = QueueName.of("staging-goclockin", "us-central1", "cloudTaskQueueTest").toString();

                log.info(" queue Path " + queuePath);


                Map<String,String> payloadMap = new HashMap<>();
                payloadMap.put("name","taskqueuetest");
                payloadMap.put("email","test@test.com");
                payloadMap.put("message","testqueue  message");

                String json = new ObjectMapper().writeValueAsString(payloadMap);

                Task.Builder taskBuilder =
                        Task.newBuilder()
                                .setHttpRequest(
                                        HttpRequest.newBuilder()
                                                .setBody(ByteString.copyFrom(json, Charset.defaultCharset()))
//                                                .setUrl("https://modules1.staging.yocoboard.com/tasks/create")
                                                .setUrl("https://www.yocoboard.com/contactUs")
                                                .setHttpMethod(HttpMethod.POST)
                                                .build());

                Task task = client.createTask(queuePath, taskBuilder.build());

                log.info("Task created: " + task.getName());

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
