package com.codigo.msregister.aggregates.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPersons {
    private String numDocument;
    private String email;
    private String telephone;
    private int documents_type_id_documents_type;
    private int enterprises_id_enterprises;
}
