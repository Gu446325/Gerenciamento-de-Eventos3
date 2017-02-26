package br.com.trisoft.eventos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.trisoft.eventos.dao.EventoDAO;
import br.com.trisoft.eventos.model.Evento;

@FacesConverter(forClass = Evento.class)
public class EventoConverter implements Converter {

	@Inject
	private EventoDAO eventoDAO;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Evento retorno = null;
		
		if (StringUtils.isNotBlank(value)) {
			Long id = new Long(value);
			retorno = eventoDAO.buscarPeloCodigo(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Long codigo = ((Evento) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}
