package com.trisoft.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.trisoft.dao.JogadorDAO;
import com.trisoft.model.Jogador;

@FacesConverter(forClass = Jogador.class)
public class JogadorConverter implements Converter {

	@Inject
	private JogadorDAO jogadorRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Jogador retorno = null;

		if (StringUtils.isNotBlank(value)) {
			Long codigo = new Long(value);
			retorno = this.jogadorRepository.buscarPeloCodigo(codigo);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Long codigo = ((Jogador) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}
