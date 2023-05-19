package org.myexam.commons.configs;

<<<<<<< HEAD

import com.fasterxml.jackson.core.JsonProcessingException;
=======
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
>>>>>>> adminpage
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.myexam.entities.Configs;
import org.myexam.repositories.ConfigsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigInfoService {

    private final ConfigsRepository repository;

    public <T> T get(String code, Class<T> clazz) {
<<<<<<< HEAD
        Configs configs = repository.findById(code).orElse(null);

=======
        return get(code, clazz, null);
    }

    public <T> T get(String code, TypeReference<T> type) {
        return get(code, null, type);
    }

    public <T> T get(String code, Class<T> clazz, TypeReference<T> typeReference) {
        Configs configs = repository.findById(code).orElse(null);
>>>>>>> adminpage
        if (configs == null || configs.getValue() == null || configs.getValue().isBlank()) {
            return null;
        }

        String value = configs.getValue();

        ObjectMapper om = new ObjectMapper();
        T data = null;
        try {
<<<<<<< HEAD
            data = om.readValue(value, clazz);
=======

            if (clazz == null) data = om.readValue(value, typeReference);
            else data = om.readValue(value, clazz);

>>>>>>> adminpage
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return data;
    }
}
