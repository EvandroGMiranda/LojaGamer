package org.generation.lojagamer.Repository;

import java.util.List;

import org.generation.lojagamer.Model.temaGamer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface GamerTemaRepository extends JpaRepository<temaGamer, Long> {
	public List<temaGamer> findAllByDescricaoContainingIgnoreCase(@Param("Descricao")String descricao );
}
