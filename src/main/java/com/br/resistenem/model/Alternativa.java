package com.br.resistenem.model;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

public class Alternativa {
	@Id
	public String id;
	public String idQuestao;
	@NonNull
	public Questao questao;
	@NonNull
	public String alternativa;
	@NonNull
	public Boolean respostaCorreta;
	
	
	public Alternativa() {
		super();
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getIdQuestao() {
		return idQuestao;
	}


	public void setIdQuestao(String idQuestao) {
		this.idQuestao = idQuestao;
	}


	public String getAlternativa() {
		return alternativa;
	}


	public void setAlternativa(String alternativa) {
		this.alternativa = alternativa;
	}


	public Boolean getRespostaCorreta() {
		return respostaCorreta;
	}


	public void setRespostaCorreta(Boolean respostaCorreta) {
		this.respostaCorreta = respostaCorreta;
	}


	public Questao getQuestao() {
		return questao;
	}


	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	
}
