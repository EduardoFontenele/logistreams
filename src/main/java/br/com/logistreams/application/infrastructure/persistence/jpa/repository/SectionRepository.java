package br.com.logistreams.application.infrastructure.persistence.jpa.repository;

import br.com.logistreams.application.infrastructure.persistence.jpa.entity.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {
}
