package com.rbalazs.orders.dto;

import com.rbalazs.orders.model.Order;
import lombok.Data;

import java.util.List;

/**
 * Represents a given Quote, which represents the Cart´s Items a given Customer wants to purchase. In case all the
 * business logic executes as expected, the Quote will be converted into a {@link Order}.
 *
 * @author Rodrigo Balazs
 */
@Data
public class QuoteDTO {

    private String customerEmail;
    private List<QuoteItemDTO> items;
}
