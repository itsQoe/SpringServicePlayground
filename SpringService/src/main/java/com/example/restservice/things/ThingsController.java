package com.example.restservice.things;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/things")
@RequiredArgsConstructor
public class ThingsController {

    private final ThingRepository thingRepo;

    @GetMapping
    public Iterable<Thing> getThings() {
        return thingRepo.findAll();
    }

    @PutMapping
    public Thing updateThing(@Valid @RequestBody ThingCreationDTO thingDTO) {
        Thing newThing = new Thing(null, thingDTO.name(), thingDTO.description());
        return thingRepo.save(newThing);
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

}

