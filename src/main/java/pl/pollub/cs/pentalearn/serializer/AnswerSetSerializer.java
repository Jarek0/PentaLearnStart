package pl.pollub.cs.pentalearn.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pl.pollub.cs.pentalearn.domain.AnswerSet;

import java.io.IOException;

/**
 * Created by pglg on 05-06-2016.
 */
public class AnswerSetSerializer extends JsonSerializer<AnswerSet> {
    @Override
    public void serialize(AnswerSet answerSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
       // jsonGenerator.wri
    }
}
