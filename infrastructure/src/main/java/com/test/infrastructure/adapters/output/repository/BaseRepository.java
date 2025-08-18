package com.test.infrastructure.adapters.output.repository;

import com.test.infrastructure.mapper.EntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseRepository<E, D, ID> {
    protected final JpaRepository<E, ID> jpaRepository;
    protected final EntityMapper<E, D> mapper;

    protected BaseRepository(JpaRepository<E, ID> jpaRepository, EntityMapper<E, D> mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    protected D mapToDomain(E entity) {
        return mapper.toDomain(entity);
    }

    protected E mapToEntity(D domain) {
        return mapper.toEntity(domain);
    }

    protected List<D> mapToDomainList(List<E> entities) {
        return entities.stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }
}
