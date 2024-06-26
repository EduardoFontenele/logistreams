package br.com.logistreams.application.adapters.persistence.jpa.repository;

import br.com.logistreams.application.adapters.persistence.jpa.entity.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSectionRepository extends JpaRepository<SectionEntity, Long> {
}
