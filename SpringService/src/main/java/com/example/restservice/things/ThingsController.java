package com.example.restservice.things;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/things")
@RequiredArgsConstructor
@Slf4j
public class ThingsController {

    private final ThingRepository thingRepo;

    @GetMapping
    public Collection<ThingView> getThings() {
        return thingRepo.findByUserId(33L);
    }

    @PutMapping
    public Thing createThing(@Valid @RequestBody ThingCreationDTO thingDTO) {
        Thing newThing = new Thing(null, thingDTO.name(), thingDTO.description(), 33L);
        return thingRepo.save(newThing);
    }

    @PostMapping("/{thingId}")
    public Thing updateThing(@Valid @RequestBody ThingUpdateDTO thingDTO, @PathVariable Long thingId) {
        var existing = thingRepo.findById(thingId);

        var updatedThing = new Thing(thingId, thingDTO.name(), thingDTO.description(), existing.getUserId());
        return thingRepo.save(updatedThing);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericException(Exception ex) {
        log.error(ex.toString());
        return ex.getMessage();
    }

}

