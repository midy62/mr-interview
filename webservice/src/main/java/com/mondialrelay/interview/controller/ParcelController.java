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

    /**
     * Updates an existing parcel's information.
     *
     * @param parcelId      The ID of the parcel to update.
     * @param updatedParcel The updated parcel information.
     * @return A ResponseEntity containing the updated parcel DTO.
     */
    @PutMapping("/{parcelId}")
    public ResponseEntity<ParcelDto> updateParcel(@PathVariable Long parcelId, @RequestBody ParcelDto updatedParcel) {
        return ResponseEntity.ok(parcelService.updateParcel(parcelId, updatedParcel));
    }

    /**
     * Deletes a parcel by its ID.
     *
     * @param parcelId The ID of the parcel to delete.
     * @return A ResponseEntity with no content.
     */
    @DeleteMapping("/{parcelId}")
    public ResponseEntity<Void> deleteParcel(@PathVariable Long parcelId) {
        parcelService.deleteParcel(parcelId);
        return ResponseEntity.noContent().build();
    }

}
