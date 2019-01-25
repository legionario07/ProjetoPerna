package br.com.projetoperna.utils;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.projetoperna.model.Produto;

/**
 * Created by PauLinHo on 26/07/2017.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */

@Component
public class JsonCalendarDeserializer extends JsonDeserializer<Calendar> {

	@Override
	public Calendar deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		Calendar c = Calendar.getInstance();
		Produto produto = (Produto) p.getParsingContext().getParent().getCurrentValue();
		return produto.getDataCadastro();

	}

}
