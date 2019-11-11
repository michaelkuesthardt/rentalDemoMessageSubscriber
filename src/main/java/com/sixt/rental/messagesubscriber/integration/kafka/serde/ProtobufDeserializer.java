package com.sixt.rental.messagesubscriber.integration.kafka.serde;

import com.google.common.base.Charsets;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.sixt.rental.demo.events.RawBookEvent;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

@Log4j2
public class ProtobufDeserializer implements Deserializer<RawBookEvent> {
    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public RawBookEvent deserialize(String topic, byte[] data) {
        try {
            RawBookEvent.Builder builder = RawBookEvent.newBuilder();
            JsonFormat.parser().ignoringUnknownFields().merge(new String(data, Charsets.UTF_8), builder);
            return builder.build();
        } catch (InvalidProtocolBufferException e) {
            log.error("Deserializing failed", e);
        }

        return null;
    }

    @Override
    public void close() {

    }
}
