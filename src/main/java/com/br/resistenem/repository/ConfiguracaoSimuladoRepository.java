package com.br.resistenem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.resistenem.model.ConfiguracaoSimulado;
import com.br.resistenem.model.Dificuldades;
import com.br.resistenem.model.Materia;
import com.br.resistenem.model.TipoSimulado;


public interface ConfiguracaoSimuladoRepository extends MongoRepository<ConfiguracaoSimulado, String> {
	ConfiguracaoSimulado findAllById(String id);

	ConfiguracaoSimulado findAllByDificuldadesAndTipoSimulado(Dificuldades dificuldades,
			TipoSimulado tipoSimulado);
}
