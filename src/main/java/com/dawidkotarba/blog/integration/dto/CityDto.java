package com.dawidkotarba.blog.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Dawid Kotarba on 08.02.2016.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDto implements Serializable {

    @NotNull
    private String name;

    private int population;

}
