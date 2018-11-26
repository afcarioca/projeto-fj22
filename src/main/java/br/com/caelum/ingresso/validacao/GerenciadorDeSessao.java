package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {
	
	private List<Sessao> sessaoDaSala;
	
	public GerenciadorDeSessao(List<Sessao> sessoesDaSala){
		this.sessaoDaSala = sessoesDaSala;
	}
	
	private boolean horarioIsConflitante(Sessao sessaoExistente, Sessao sessaoNova){
		
		LocalDate hoje = LocalDate.now();
		
		LocalTime horarioSessaoExistente = sessaoExistente.getHorario().atDate(hoje).toLocalTime();
		LocalTime horarioSessaoNova = sessaoNova.getHorario().atDate(hoje).toLocalTime();
		
		boolean terminaAntes = sessaoNova.getHorarioTermino()
				.isBefore(horarioSessaoExistente);
		
		boolean comecaDepois = sessaoNova.getHorarioTermino()
				.isBefore(horarioSessaoNova);	
		
		if(terminaAntes || comecaDepois){
			return false;
		}
		
		return true;
	}
	
	public boolean cabe(Sessao sessaoNova){
		return sessaoDaSala.stream().noneMatch(sessaoExistente ->
										horarioIsConflitante(sessaoExistente, sessaoNova)
										);
	}


}
