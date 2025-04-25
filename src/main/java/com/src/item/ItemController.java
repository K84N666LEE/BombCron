package com.src.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Operation(summary = "Get all items")
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @Operation(summary = "Get an item by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@Parameter(description = "ID of the item to be retrieved", required = true) @PathVariable Long id) {
        return itemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new item")
    @PostMapping
    public Item createItem(@Parameter(description = "Item to be created", required = true) @RequestBody Item item) {
        return itemService.createItem(item);
    }

    @Operation(summary = "Update an item by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@Parameter(description = "ID of the item to be updated", required = true) @PathVariable Long id,
                                           @Parameter(description = "Updated item details", required = true) @RequestBody Item itemDetails) {
        return ResponseEntity.ok(itemService.updateItem(id, itemDetails));
    }

    @Operation(summary = "Delete an item by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@Parameter(description = "ID of the item to be deleted", required = true) @PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
