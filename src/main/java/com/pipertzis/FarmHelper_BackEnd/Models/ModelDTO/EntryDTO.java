/* 
    Created by Piper on 10/25/2021 
*/

package com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EntryDTO {

    private UUID entryId;
    private int amount;
    private double price;
    private double weight;
    private Date date;
    private String username;
    private String fruitName;
    private String varietyName;
    private String packageName;
}
