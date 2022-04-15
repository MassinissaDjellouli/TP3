package com.form;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientForm {
    @NotNull
    @NotBlank
    @Size(min = 2)
    public String name;
    @NotNull
    @NotBlank
    @Size(min = 5)
    public String adress;
    @NotNull
    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(regexp = "[0-9]{10}")
    public String phone;
}
