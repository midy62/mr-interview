package com.mondialrelay.interview.controller;

import com.mondialrelay.business.dto.ParcelDto;
import com.mondialrelay.business.service.ParcelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.mondialrelay.interview.constant.UrlConstant.PARCELS_PATH;

@RestController
@RequestMapping(PARCELS_PATH)
@AllArgsConstructor
public class ParcelController {

    private ParcelService parcelService;

    @PutMapping("/{parcelId}")
    public ResponseEntity<ParcelDto> updateParcel(@PathVariable Long parcelId, @RequestBody ParcelDto updatedParcel) {
        return ResponseEntity.ok(parcelService.updateParcel(parcelId, updatedParcel));
    }

    @DeleteMapping("/{parcelId}")
    public ResponseEntity<Void> deleteParcel(@PathVariable Long parcelId) {
        parcelService.deleteParcel(parcelId);
        return ResponseEntity.noContent().build();
    }

}
