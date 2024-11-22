package com.example.login.controller;

import com.example.login.model.entity.Color;
import com.example.login.model.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
@CrossOrigin(origins = "*")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @PostMapping
    public Color createColor(@RequestBody Color color) {
        return colorService.createColor(color);
    }

    @GetMapping
    public List<Color> getAllColors() {
        return colorService.getAllColors();
    }

    @GetMapping("/{id}")
    public Color getColorById(@PathVariable Long id) {
        return colorService.getColorById(id);
    }

    @PutMapping("/{id}")
    public Color updateColor(@PathVariable Long id, @RequestBody Color colorDetails) {
        return colorService.updateColor(id, colorDetails);
    }

    @DeleteMapping("/{id}")
    public String deleteColor(@PathVariable Long id) {
        colorService.deleteColor(id);
        return "Color with ID " + id + " has been deleted.";
    }
}
