package br.com.personal.money.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaSpecificationExecutorProjection<T> extends JpaSpecificationExecutor<T> {
	
	<PROJECTION> Page<PROJECTION> findAll(Specification<T> spec, Class<PROJECTION> projectionClass, Pageable pageable);

}
