package com.gh.cobranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gh.cobranca.model.StatusTitulo;
import com.gh.cobranca.model.Titulo;
import com.gh.cobranca.repository.Titulos;

@Service
public class CadastroTituloService {

	@Autowired
	private Titulos titulos;

	public void salvar(Titulo titulo) {

		try {
			this.titulos.save(titulo);

		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formatado de data Invalido!");

		}
	}

	public void excluir(Long codigo) {
		titulos.deleteById(codigo);

	}

	public String receber(Long codigo) {
		Titulo titulo = titulos.getOne(codigo);
		titulo.setStatus(StatusTitulo.RECEBIDO);
		titulos.save(titulo);
		return StatusTitulo.RECEBIDO.getDescricao();

	}

}
