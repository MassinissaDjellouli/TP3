package com.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class clientForm {
    public String name;
    public String adress;
    public String phone;
}
