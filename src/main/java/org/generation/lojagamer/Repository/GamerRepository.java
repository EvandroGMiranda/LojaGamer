package org.generation.lojagamer.Repository;

import java.util.List;
import org.generation.lojagamer.Model.gamerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface GamerRepository extends JpaRepository<gamerModel, Long> {
	public List<gamerModel>findAllByNomeContainingIgnoreCase(@Param("nome") String titulo);
}

