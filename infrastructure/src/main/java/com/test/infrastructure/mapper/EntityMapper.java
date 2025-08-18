package com.test.infrastructure.mapper;

public interface EntityMapper<E, D> {
    D toDomain(E entity);
    E toEntity(D domain);
}
