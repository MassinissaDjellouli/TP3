package com.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class empruntRetourForm {
    private int bookId;
    private int clientId;
}
