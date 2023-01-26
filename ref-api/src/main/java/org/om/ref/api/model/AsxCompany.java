package org.om.ref.api.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

public @Data class AsxCompany {
    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1)
    private String code;

    @CsvBindByPosition(position = 2)
    private String gics_ind_grp;
}
