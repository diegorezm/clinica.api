package com.api.clinica.services;

import java.util.List;

public interface ServiceInterface<ENTITY, DTO> {
    List<ENTITY> getAll();
    ENTITY getById(Integer id);
    ENTITY create(DTO payload);
    ENTITY update(DTO payload, Integer id);
    void delete(Integer id);
}